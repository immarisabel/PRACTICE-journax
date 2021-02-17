package nl.marisabel.journal;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadEntry extends Entry {


    private static Scanner scanner = new Scanner(System.in);
    private final String newDate = today();
    private String newEntry;
    private int entryId = 0;

    public ReadEntry(String newDate, String newEntry) {
        super(newDate, newEntry);
    }


    public static String EntryContent(String entryContent) {
        return entryContent;
    }

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));

    }

    private int EntryId() {
        return 0;
    }



    /* _________________ ♥ ♥ ♥ ♥ ♥ GET ONE ENTRY ♥ ♥ ♥ ♥ ♥ _________________ */


    public void getEntry(int entryId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Connection  connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement stmt = connection.createStatement();

        this.entryId = entryId;

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
        String newEntry = EntryContent("TEST entry");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String q = "SELECT * FROM journal";
            ResultSet rs = statement.executeQuery(q);
            // * = all
            while(rs.next())
            {
                // read the result set
                System.out.println();
                System.out.println("Entry number: " + rs.getInt("entry_id") + "\ndate: " + rs.getString("entry_date") +"\n");
                System.out.println();
                System.out.println(rs.getString("entry_content") + "\n");
                System.out.println(".....................................");
            }


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

