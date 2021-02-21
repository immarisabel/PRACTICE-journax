//package nl.marisabel.journal;
//
//import java.sql.*;
//import java.util.Scanner;
//
//public class ModEntry {
//
//    private static final Scanner scanner = new Scanner(System.in);
//
//    private final String newDate = Date.today();
//
//    public void updateEntry (int entryId) throws ClassNotFoundException, SQLException {
//
//        Class.forName("org.sqlite.JDBC");
//        Connection connection = null;
//
//            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);
//            System.out.println("Please update your entry...");
//            String newEntry = scanner.nextLine();
//            PreparedStatement prep = connection.prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
//            prep.setString(1, newEntry);
//            prep.setInt(2, entryId);
//            prep.execute();
//
//            System.out.println(">>>>>>>> ENTRY UPDATED");
//
//
//        }
//    }
//
