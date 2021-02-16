package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class ModEntry extends Entry{
    public ModEntry(String newDate, String newEntry) {
        super(newDate, newEntry);
    }
    private static Scanner scanner = new Scanner(System.in);
    private final String newDate =Date.today();
    private  String newEntry;
    private int entryID;

    public void updateEntry (int entryID, String newEntry) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.entryID = entryID;
        this.newEntry = newEntry;
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setInt(2, entryID);
            prep.execute();

            System.out.println(">>>>>>>> ENTRY UPDATED");

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
