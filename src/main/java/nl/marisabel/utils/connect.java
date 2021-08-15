package nl.marisabel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {


	public Connection c() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
		return connection;
	}
}
