package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.marisabel.utils.connect;

public class ManageJournal {

	connect c = new connect();

	public void createJournal() throws ClassNotFoundException, SQLException {

		String journalTable = "CREATE TABLE IF NOT EXISTS journal(" + "entry_id INTEGER NOT NULL UNIQUE,"
				+ "entry_date TEXT NOT NULL," + "entry_content MEDIUMTEXT," + "cat_id INTEGER DEFAULT 1,"
				+ "PRIMARY KEY(entry_id AUTOINCREMENT))";

		String categoriesTable = "CREATE TABLE IF NOT EXISTS categories (" + "cat_id INTEGER NOT NULL UNIQUE,"
				+ "category TEXT DEFAULT main NOT NULL," + "PRIMARY KEY (cat_id AUTOINCREMENT))";

		String loginTable = "CREATE TABLE IF NOT EXISTS login (" + "password TEXT," + "reminder TEXT,"
				+ "username TEXT)";
		Statement s = c.c().createStatement();
		s.setQueryTimeout(30);
		try {
			s.setQueryTimeout(30);
			s.executeUpdate(journalTable);
			s.executeUpdate(categoriesTable);
			s.execute(loginTable);
			System.out.println("Tables Created");

			Statement stmt = c.c().createStatement();
			// this query will check that there is only 1 row on the database
			String query = "select 1 from categories limit 1";
			ResultSet rs = stmt.executeQuery(query);
			int rowCount = 0;
			while (rs.next()) {
				// Increment rowCount by 1
				rowCount++;
				// Process the result set data for the current row
			}
			if (rowCount < 1) {
				// if no category found, create default category
				PreparedStatement prep = c.c()
						.prepareStatement("INSERT INTO categories (category, cat_id) VALUES (?,?)");
				prep.setString(1, "default");
				prep.setInt(2, 1);
				prep.execute();
			}

			System.out.println(">>>>>>>> journal created");
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (c.c() != null)
					c.c().close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}

	public void delJournal() throws ClassNotFoundException, SQLException {
		Statement s = c.c().createStatement();
		s.setQueryTimeout(30);
		String tables = "drop table if exists journal;" + "drop table if exists categories;"
				+ "drop table if exists login";
		try {
			// create a database connection
			s.executeUpdate(tables);
			System.out.println("Tables Dropped");
			System.out.println(">>>>>>>> journal deleted");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (c.c() != null)
					c.c().close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}

}
