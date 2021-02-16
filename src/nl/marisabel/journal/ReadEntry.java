package nl.marisabel.journal;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadEntry extends Entry{


    private static Scanner scanner = new Scanner(System.in);
    private final String newDate =today();
    private  String newEntry;
    private int entryId = 0;

    public ReadEntry(String newDate, String newEntry) {
        super(newDate, newEntry);
    }


    public static String EntryContent(String entryContent){
        return entryContent;
    }

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));

    }

    private int EntryId() {  return 0;  }

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
    

    public void getEntry(int entryId, String newEntry ) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.entryId = entryId;
        this.newEntry = EntryContent("TEST entry");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String q = "SELECT entry_content FROM journal where entry_id = ?";
            ResultSet rs = statement.executeQuery(q);
            while(rs.next()) {
                System.out.println(rs.getArray("entry_content") + "\n");
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

