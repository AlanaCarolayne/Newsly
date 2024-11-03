package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factory.ConnectionFactory;
import model.Categoria;
import model.Noticia;
import model.Usuario;

public class NoticiaDAO {
	Connection connection;

	public void inserir(Noticia n) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO NOTICIA_NL(ID_USUARIO, ID_CATEGORIA, TITULO, DESCRICAO, DATA_NOTICIA) VALUES (?,?,?,?,?)";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, n.getUsuario().getId());
			stmt.setInt(2, n.getCategoria().getId());
			stmt.setString(3, n.getTitulo());
			stmt.setString(4, n.getDesc());
			stmt.setDate(5, new Date(n.getData().getTime()));
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

	public List<Noticia> listar() {
		List<Noticia> noticias = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT N.ID_NOTICIA, N.TITULO, N.DESCRICAO, N.DATA_NOTICIA, U.ID_USUARIO, U.NOME, U.EMAIL, U.SENHA, "
				+ "C.ID_CATEGORIA,C.CATEGORIA  FROM NOTICIA_NL N JOIN USUARIOS_NL U ON N.ID_USUARIO = U.ID_USUARIO "
				+ " JOIN CATEGORIA_NL C ON N.ID_CATEGORIA = C.ID_CATEGORIA";
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getNString("NOME"));
				u.setEmail(rs.getString("EMAIL"));
				u.setSenha(rs.getNString("SENHA"));
				u.setId(rs.getInt("ID_USUARIO"));
				Categoria c = new Categoria();
				c.setId(rs.getInt("ID_CATEGORIA"));
				c.setCategoria(rs.getNString("CATEGORIA"));
				Noticia n = new Noticia();
				n.setId(rs.getInt("ID_NOTICIA"));
				n.setTitulo(rs.getNString("TITULO"));
				n.setDesc(rs.getNString("DESCRICAO"));
				n.setCategoria(c);
				n.setUsuario(u);
				noticias.add(n);
				
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
		return noticias;
	}

	public void atualizar(Noticia n) {
		PreparedStatement stmt = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String sql = "UPDATE NOTICIA_NL SET ID_USUARIO=?, ID_CATEGORIA=?, TITULO=?, DESCRICAO=?, DATA_NOTICIA=? WHERE ID_NOTICIA=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, n.getUsuario().getId());
			stmt.setInt(2, n.getCategoria().getId());
			stmt.setString(3, n.getTitulo());
			stmt.setString(4, n.getDesc());
			stmt.setDate(5, new Date(n.getData().getTime()));
			stmt.setInt(6, n.getId());
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
		String sql = "DELETE FROM NOTICIA_NL WHERE ID_NOTICIA=?";
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
