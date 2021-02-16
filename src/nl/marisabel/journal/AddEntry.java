package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class AddEntry extends Entry{
    public AddEntry(String newDate, String newEntry) {
        super(newDate, newEntry);
    }

    private static Scanner scanner = new Scanner(System.in);
    private  String newDate =Date.today();
    private  String newEntry;
    private int entryID;



    public void addEntry(String newDate, String newEntry) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.newDate = newDate;
        this.newEntry = newEntry;
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("INSERT INTO journal (entry_content, entry_date) VALUES (?,?)"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setString(2, newDate);
            prep.execute();

            System.out.println(">>>>>>>> ENTRY ADDED");




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
