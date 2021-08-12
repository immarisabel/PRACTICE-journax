package nl.marisabel.journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

	public static Connection c() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
		return connection;
	}
}
