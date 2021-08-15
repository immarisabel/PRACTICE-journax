package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import nl.marisabel.utils.connect;

public class NewJournal {
	connect c = new connect();

    public void createJournal() throws ClassNotFoundException {


        String journalTable = "CREATE TABLE journal ("
                + "entry_id INTEGER NOT NULL UNIQUE,"
                + "entry_date TEXT NOT NULL,"
                + "entry_content MEDIUMTEXT,"
                +"cat_id INTEGER DEFAULT 1,"
                + "PRIMARY KEY(entry_id AUTOINCREMENT))";

        String categoriesTable = "CREATE TABLE categories ("
                + "cat_id INTEGER NOT NULL UNIQUE,"
                + "category TEXT NOT NULL DEFAULT 'no category',"
                + "PRIMARY KEY(cat_id AUTOINCREMENT))";

        try {
			Statement statement = c.c().createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(journalTable);
            statement.executeUpdate(categoriesTable);
            System.out.println("Tables Created");

            PreparedStatement prep = c.c().prepareStatement("INSERT INTO categories (category, cat_id) VALUES (?,?)");
            prep.setString(1, "default");
            prep.setInt(2, 1);
            prep.execute();

            System.out.println(">>>>>>>> journal created");


        } catch (
                SQLException e) {
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

    public void delJournal() throws ClassNotFoundException {

        String tables = "drop table if exists journal;" +
                "drop table if exists categories;";

        try {
            // create a database connection
			Statement statement = c.c().createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(tables);
            System.out.println("Tables Dropped");

            System.out.println(">>>>>>>> journal deleted");


        } catch (
                SQLException e) {
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


}
