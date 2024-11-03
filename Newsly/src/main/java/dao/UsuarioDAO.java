package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
	private Connection connection;

	public void inserir(Usuario u) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		try {
			String sql = "INSERT INTO USUARIOS_NL(NOME, EMAIL, SENHA) VALUES (?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getSenha());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Usuario> listar(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM USUARIOS_NL";
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getNString("NOME"));
				u.setEmail(rs.getString("EMAIL"));
				u.setSenha(rs.getNString("SENHA"));
				u.setId(rs.getInt("ID_USUARIO"));
				usuarios.add(u);
			}
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}
	
	public void atualizar(Usuario u) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "UPDATE USUARIOS_NL SET NOME=?, EMAIL=?, SENHA=? WHERE ID_USUARIO=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getSenha());
			stmt.setInt(4, u.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void excluir(int id) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "DELETE FROM USUARIOS_NL WHERE ID_USUARIO=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
