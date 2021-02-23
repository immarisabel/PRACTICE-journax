package nl.marisabel.journal;

import java.sql.*;

public class Search {
    String searchWord;

    public void searchEntries(String searchWord) throws ClassNotFoundException, SQLException {
        this.searchWord = searchWord;

        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        PreparedStatement prep = connection.prepareStatement("SELECT * FROM journal WHERE entry_content LIKE ? ");
        prep.setString(1, "%" + searchWord + "%");
        prep.execute();

        ResultSet rs = prep.getResultSet();
        while(rs.next())
        {
            System.out.println();
            System.out.println("Entry number: " + rs.getInt("entry_id") + "\ndate: " + rs.getString("entry_date") +"\ncategory: " + rs.getString("cat_id")+"\n");
            System.out.println();
            System.out.println(rs.getString("entry_content") + "\n");
            System.out.println(".....................................");
        }

    }
}
