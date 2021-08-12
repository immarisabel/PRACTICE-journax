package nl.marisabel.journal;


import java.sql.*;
import java.util.Scanner;


public class Entries {

    private static final Scanner scanner = new Scanner(System.in);
    private String newEntry;
    private int entryId = 0;

    public void addEntry(String newDate, String newEntry, int categoryId) throws ClassNotFoundException, SQLException {
    	       
        PreparedStatement prep = connect.c().prepareStatement("INSERT INTO journal (entry_content, entry_date, cat_id) VALUES (?,?,?)");
        prep.setString(1, newEntry);
        prep.setString(2, Date.today());
        prep.setInt(3, categoryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY ADDED");
        connect.c().close();
    }

    public void updateEntry (int entryId) throws ClassNotFoundException, SQLException {

        Statement statement = connect.c().createStatement();
        statement.setQueryTimeout(30);

        System.out.println("Please update your entry...");
        String newEntry = scanner.nextLine();

        PreparedStatement prep = connect.c().prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
        prep.setString(1, newEntry);
        prep.setInt(2, entryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY UPDATED");
        connect.c().close();

    }

    public void getEntry(int entryId) throws SQLException, ClassNotFoundException {

        this.entryId = entryId;

        PreparedStatement prep = connect.c().prepareStatement("SELECT * FROM journal WHERE entry_id = ?");
        prep.setInt(1, entryId);
        prep.execute();
        ResultSet rs = prep.getResultSet();

        while(rs.next()){
            System.out.println(rs.getString("entry_content"));
        }
        connect.c().close();
    }

    public void getEntries() throws ClassNotFoundException, SQLException {
      
        Statement statement = connect.c().createStatement();
        statement.setQueryTimeout(30);
        String query = "SELECT * FROM journal LEFT JOIN categories WHERE journal.cat_id = categories.cat_id";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next())
        {
            System.out.println();
            System.out.println("Entry number: " + rs.getInt("entry_id"));
            System.out.println(rs.getString("entry_date"));
            System.out.println("Category: "+rs.getString("category"));
            System.out.println();
            System.out.println(rs.getString("entry_content") + "\n");
            System.out.println(".....................................");
        }
        connect.c().close();

    }

    public void deleteEntry(int entryId) throws ClassNotFoundException, SQLException {
        this.entryId = entryId;
        PreparedStatement prep = connect.c().prepareStatement("DELETE FROM journal WHERE entry_id = ?");
        prep.setInt(1, entryId);
        prep.execute();

        System.out.println(">>>>>>>> ENTRY DELETED");
        connect.c().close();


    }
}

