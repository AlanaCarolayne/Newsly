package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory connection;

	private ConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		if (connection == null) {
			connection = new ConnectionFactory();
		}
		return connection;
	}

	public Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return c;

	}

}
