import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SQLite extends Entry{


    private  String newTitle;
    private  String newDate =today();
    private  String newEntry;
    private int entryID;

    public SQLite(String newTitle, String newDate, String newEntry) {
        super(newTitle, newDate, newEntry);
    }


    public static String EntryTitle(String entryTitle){
        return entryTitle;
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


    public void addEntry(String newTitle, String newDate, String newEntry) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.newTitle = newTitle;
        this.newDate = newDate;
        this.newEntry = newEntry;
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("INSERT INTO journal (entry_content, entry_date, entry_title) VALUES (?,?,?)"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setString(2, newDate);
            prep.setString(3, newTitle);
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


    public void getEntries() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String newTitle = EntryTitle("TEST title");
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
                System.out.println("Entry number: " + rs.getInt("entry_id") + "\ndate: " + rs.getString("entry_date") +"\n♥ " + rs.getString("entry_title").toUpperCase() + "\n");
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


    public void updateEntry (int entryID, String newTitle , String newEntry) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.newTitle = newTitle;
        this.newEntry = newEntry;
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ?, entry_title=? WHERE entry_id = ?"); /* TIME FORMAT! */
            prep.setString(1, newEntry);
            prep.setString(2, newTitle);
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

