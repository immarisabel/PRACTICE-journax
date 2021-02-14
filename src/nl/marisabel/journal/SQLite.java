package nl.marisabel.journal;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SQLite extends Entry{


    private static Scanner scanner = new Scanner(System.in);
    private  String newDate =today();
    private  String newEntry;
    private int entryID;

    public SQLite(String newDate, String newEntry) {
        super(newDate, newEntry);
    }


    public static String EntryContent(String entryContent){
        return entryContent;
    }

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        String todayTime = dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));
        return todayTime;

    }

    private int EntryID() {  return 0;  }

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

   /*READ*/













    //TODO Create a query to retrieve only 1 entry as selected per ID.
    /*NEED to do all of this on updateEntry  by selecting only 1 entry.*/


















    public void getEntry() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String newDate = today();
        String newEntry = EntryContent("TEST entry");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            System.out.println("Type the entry number you wish to update...");
            entryId = scanner.nextInt();

            String q = "SELECT entry_content FROM journal where entry_id =? LIMIT 1";
            ResultSet rs = statement.executeQuery(q);
            // * = all
            while(rs.next())
            {
                // read the result set
                System.out.println("Current content: \n ");
                System.out.println();
                System.out.println(rs.getString("entry_content") + "\n");
                System.out.println("How should it be updated?");
                entry = scanner.nextLine();
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

    /* DELETE */

    public void deleteEntry(int entryID) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        this.entryID = entryID;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement prep = connection.prepareStatement("DELETE FROM journal WHERE entry_id = ?");
            prep.setInt(1, entryID);
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

    public void updateEntry (int entryID, String newEntry) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.newEntry = newEntry;
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ?, WHERE entry_id = ?"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setInt(3, entryID);
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

