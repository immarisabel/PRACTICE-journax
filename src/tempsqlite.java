//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class tempsqlite {
//
//
//    public static void database() throws ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//
//        Connection connection = null;
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:journaxBD.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//            // statement.executeUpdate("drop table if exists tableName");
//
//            /* ADD STATEMENT */
//            //statement.executeUpdate("insert into tableName values(1, 'NEW', 40)");
//
//            /* DELETE STATEMENT */
//            // statement.executeUpdate("DELETE FROM tableName WHERE id = 1");
//
//            /* FULL TABLE DISPLAY */
//            String q = "SELECT * FROM tableName";
//            ResultSet rs = statement.executeQuery(q);
//            // * = all
//            while(rs.next())
//            {
//                // read the result set
//                System.out.println("date: " + rs.getInt("entry_date"));
//                System.out.println("title: " + rs.getString("entry_title") + "\n");
//                System.out.println(rs.getString("entry_content") + "\n");
//            }
//
//
//            /* ONLY MAX score DISPLAY */
//            String q = "SELECT * FROM journal\n";
//            ResultSet rs = statement.executeQuery(q);
//            // * = all
//            if (rs.next()) {
//                // read the result set
//                System.out.println("date: " + rs.getInt("entry_date"));
//                System.out.println("title: " + rs.getString("entry_title") + "\n");
//                System.out.println(rs.getString("entry_content") + "\n");
//
//            }
//
//
//        } catch (
//                SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e.getMessage());
//            }
//        }
//    }
//}