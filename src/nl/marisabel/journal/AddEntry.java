package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class AddEntry{

    private static final Scanner scanner = new Scanner(System.in);

    public void addEntry(String newDate, String newEntry, int categoryId) throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("INSERT INTO journal (entry_content, entry_date, cat_id) VALUES (?,?,?)");
            prep.setString(1, newEntry);
            prep.setString(2, newDate);
            prep.setInt(3, categoryId);
            prep.execute();

            System.out.println(">>>>>>>> ENTRY ADDED");

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
