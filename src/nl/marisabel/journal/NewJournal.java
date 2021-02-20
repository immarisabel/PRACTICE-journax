package nl.marisabel.journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewJournal {

    public void createJournal() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;


        String journalTable = "CREATE TABLE journal ("
                + "entry_id INTEGER NOT NULL UNIQUE,"
                + "entry_date TEXT NOT NULL,"
                + "entry_content TEXT,"
                +"cat_id INTEGER DEFAULT 1,"
                + "PRIMARY KEY(entry_id AUTOINCREMENT))";

        String categoriesTable = "CREATE TABLE categories ("
                + "cat_id INTEGER NOT NULL UNIQUE,"
                + "category TEXT NOT NULL DEFAULT 'no category',"
                + "PRIMARY KEY(cat_id AUTOINCREMENT))";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(journalTable);
            statement.executeUpdate(categoriesTable);
            System.out.println("Tables Created");

            System.out.println(">>>>>>>> journal created");


        } catch (
                SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public void delJournal() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;


        String tables = "drop table if exists journal;" +
                "drop table if exists categories;";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
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
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }


}
