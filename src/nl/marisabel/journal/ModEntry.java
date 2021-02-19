package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class ModEntry {

    private static Scanner scanner = new Scanner(System.in);

    private final String newDate = Date.today();
    private String newEntry;
    private int entryId = 0;

    public void updateEntry (int entryId) throws ClassNotFoundException {

       this.entryId = entryId;

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            System.out.println("Please update your entry...");
            newEntry = scanner.nextLine();
            PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setInt(2, entryId);
            prep.execute();

            System.out.println(">>>>>>>> ENTRY UPDATED");

        } catch (
                SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
