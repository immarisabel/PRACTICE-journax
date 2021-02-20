package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class Categories {

    private static final Scanner scanner = new Scanner(System.in);

    public void addCategory() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        
        System.out.println("Add a category...");
        String category = scanner.nextLine();
        
        PreparedStatement prep = connection.prepareStatement("INSERT INTO categories (category) VALUES (?) ");
        prep.setString(1, category);
        prep.execute();

        System.out.println(">>>>>>>> CATEGORY ADDED");

    }

    public void delCategory(int catId) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        PreparedStatement prep = connection.prepareStatement("DELETE FROM categories WHERE cat_id = ?");
        prep.setInt(1, catId);
        prep.execute();

        System.out.println(">>>>>>>> CATEGORY DELETED");
    }

    public void seeCategories () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT * FROM categories";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next())
        {
            System.out.println(rs.getInt("cat_Id") + ": "+rs.getString("category"));
        }

    }

    public void updateCategory (int catId) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            System.out.println("Please update the category...");
            String newCat = scanner.nextLine();
            PreparedStatement prep = connection.prepareStatement("UPDATE categories set category = ? WHERE cat_id = ?");
            prep.setString(1, newCat);
            prep.setInt(2, catId);
            prep.execute();

            System.out.println(">>>>>>>> CATEGORY UPDATED");

    }

    public void printActions() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - to close\n" +
                "1  - see all categories\n" +
                "2  - to add a new category\n" +
                "3  - to modify a category\n" +
                "4  - to remove a category\n"
        );

        System.out.println("\nWhat do you wish to do?");
    }

    public void catOptions() throws SQLException, ClassNotFoundException {

        boolean quit = false;
        while(!quit)
        {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action)
            {
                case 0:
                    quit = true;
                    break;
                case 1:
                    seeCategories();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    System.out.println("Type the category ID number you wish to update...");
                    int catId = scanner.nextInt();
                    int newCatId = catId;
                   // journal.getEntry(entryId);
                    System.out.println("Do you wish to update it? Type 1. yes or 2. no");
                    int answer = scanner.nextInt();
                    if (answer == 1)
                    {
                        updateCategory(newCatId);
                    }


                    break;
                case 4:
                    System.out.println("Type the category number you wish to delete...");
                    catId = scanner.nextInt();
                    //getCategory(catId);
                    System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
                    answer = scanner.nextInt();
                    if (answer == 1)
                    {
                       delCategory(catId);
                    }
                    delCategory(0);
                    break;
            }
        }

    }
}
