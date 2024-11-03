package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Categoria;
import model.Comentario;
import model.Noticia;
import model.Usuario;

public class ComentarioDAO {
	Connection connection;

	public void inserir(Comentario c) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO COMENTARIO_NL(ID_USUARIO, ID_NOTICIA, COMENTARIO) VALUES (?,?,?)";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, c.getUsuario().getId());
			stmt.setInt(2, c.getNoticia().getId());
			stmt.setString(3, c.getComentario());
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

	public List<Comentario> listar() {
		List<Comentario> comentarios = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT C.ID_COMENTARIO, C.COMENTARIO,N.ID_NOTICIA,N.ID_CATEGORIA, N.TITULO, N.DESCRICAO, N.DATA_NOTICIA,"
				+ " U.ID_USUARIO, U.NOME, U.EMAIL," + "U.SENHA "
				+ "FROM COMENTARIO_NL C JOIN NOTICIA_NL N ON C.ID_NOTICIA = N.ID_NOTICIA  "
				+ " JOIN USUARIOS_NL U ON C.ID_USUARIO = U.ID_USUARIO";
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getNString("NOME"));
				u.setEmail(rs.getString("EMAIL"));
				u.setSenha(rs.getNString("SENHA"));
				u.setId(rs.getInt("ID_USUARIO"));
				Comentario c = new Comentario();
				c.setId(rs.getInt("ID_COMENTARIO"));
				c.setComentario(rs.getNString("COMENTARIO"));
				Noticia n = new Noticia();
				n.setId(rs.getInt("ID_NOTICIA"));
				n.setTitulo(rs.getNString("TITULO"));
				n.setDesc(rs.getNString("DESCRICAO"));
				c.setNoticia(n);
				c.setUsuario(u);
				comentarios.add(c);
			
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comentarios;
	}

	public void atualizar(Comentario c) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "UPDATE COMENTARIO_NL SET ID_USUARIO=?, ID_NOTICIA=?, COMENTARIO=? WHERE ID_COMENTARIO=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, c.getUsuario().getId());
			stmt.setInt(2, c.getNoticia().getId());
			stmt.setString(3, c.getComentario());
			stmt.setInt(4, c.getId());
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

	public void deletar(int id) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "DELETE FROM COMENTARIO_NL WHERE ID_COMENTARIO=?";
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
