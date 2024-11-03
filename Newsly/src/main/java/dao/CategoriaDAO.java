package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import model.Categoria;

public class CategoriaDAO {
	Connection c;

	public List<Categoria> listarCategoria() {
		List<Categoria> categorias = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CATEGORIA_NL";
		c = ConnectionFactory.getInstance().getConnection();
		try {
			stmt = c.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Categoria cat = new Categoria();
				cat.setId(rs.getInt("ID_CATEGORIA"));
				cat.setCategoria(rs.getNString("CATEGORIA"));
				categorias.add(cat);
			}
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categorias;
	}

}
