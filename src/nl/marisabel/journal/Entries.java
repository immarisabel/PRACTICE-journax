package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class Entries {

    private static final Scanner scanner = new Scanner(System.in);
    private final String newDate = Date.today();
    private String newEntry;
    private int entryId = 0;

    public void addEntry(String newDate, String newEntry, int categoryId) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        PreparedStatement prep = connection.prepareStatement("INSERT INTO journal (entry_content, entry_date, cat_id) VALUES (?,?,?)");
        prep.setString(1, newEntry);
        prep.setString(2, newDate);
        prep.setInt(3, categoryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY ADDED");

    }

    public void updateEntry (int entryId) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        System.out.println("Please update your entry...");
        String newEntry = scanner.nextLine();

        PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
        prep.setString(1, newEntry);
        prep.setInt(2, entryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY UPDATED");


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

    public void getEntries() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT * FROM journal";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next())
        {
            System.out.println();
            System.out.println("Entry number: " + rs.getInt("entry_id") + "\ndate: " + rs.getString("entry_date") +"\ncategory: " + rs.getString("cat_id")+"\n");
            System.out.println();
            System.out.println(rs.getString("entry_content") + "\n");
            System.out.println(".....................................");
        }

    }

    public void deleteEntry(int entryId) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        this.entryId = entryId;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        PreparedStatement prep = connection.prepareStatement("DELETE FROM journal WHERE entry_id = ?");
        prep.setInt(1, entryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY DELETED");


    }
}

