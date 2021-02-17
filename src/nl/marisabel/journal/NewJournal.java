package nl.marisabel.journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewJournal {

    public void createJournal() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;


        String myTableName = "CREATE TABLE journal ("
                + "entry_id INTEGER NOT NULL UNIQUE,"
                + "entry_date TEXT NOT NULL,"
                + "entry_content TEXT,"
                + "PRIMARY KEY(entry_id AUTOINCREMENT))";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");

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


        String myTableName = "DROP TABLE journal";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");

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
