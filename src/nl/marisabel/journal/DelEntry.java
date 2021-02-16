package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class DelEntry extends Entry{
    public DelEntry(String newDate, String newEntry) {
        super(newDate, newEntry);
    }
    private static Scanner scanner = new Scanner(System.in);
    private  String newDate =Date.today();
    private  String newEntry;
    private int entryId;

    /* DELETE */

    public void deleteEntry(int entryId) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        this.entryId = entryId;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("DELETE FROM journal WHERE entry_id = ?");
            prep.setInt(1, entryId);
            prep.execute();


            System.out.println(">>>>>>>> ENTRY DELETED");




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
