package nl.marisabel.journal;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadEntry {

    private static Scanner scanner = new Scanner(System.in);

    private final String newDate = today();
    private String newEntry;
    private int entryId = 0;

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));

    }

    public void getEntry(int entryId) throws SQLException, ClassNotFoundException {

        this.entryId = entryId;

        Class.forName("org.sqlite.JDBC");
        Connection  connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement stmt = connection.createStatement();

        PreparedStatement prep = connection.prepareStatement("SELECT * FROM journal WHERE entry_id = ?");
        prep.setInt(1, entryId);
        prep.execute();
        ResultSet rs = prep.getResultSet();

        while(rs.next()){
            System.out.println(rs.getString("entry_content"));
        }
    }


    public void getEntries() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String newDate = today();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM journal";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                System.out.println();
                System.out.println("Entry number: " + rs.getInt("entry_id") + "\ndate: " + rs.getString("entry_date") +"\n");
                System.out.println();
                System.out.println(rs.getString("entry_content") + "\n");
                System.out.println(".....................................");
            }

        } catch (
                SQLException e) {
            System.err.println(e.getMessage());
        } finally
        {

        }

    }

}

