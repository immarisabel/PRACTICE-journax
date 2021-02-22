package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;

public class Categories {

    private static Scanner scanCat = new Scanner(System.in);
    private String newCategory;
    private int catId = 0;

    public void addCategory(String newCategory) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        PreparedStatement prep = connection.prepareStatement("INSERT INTO categories (category) VALUES (?) ");
        prep.setString(1, newCategory);
        prep.execute();

        System.out.println(">>>>>>>> CATEGORY ADDED");

    }

    public void updateCategory (int catId) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        System.out.println("Please update the category...");
        String newCategory = scanCat.nextLine();

        PreparedStatement prep = connection.prepareStatement("UPDATE categories set category = ? WHERE cat_id = ?");
        prep.setString(1, newCategory);
        prep.execute();

        System.out.println(">>>>>>>> CATEGORY UPDATED");

    }

    public void getCategory(int catId) throws SQLException, ClassNotFoundException {

        this.catId = catId;

        Class.forName("org.sqlite.JDBC");
        Connection  connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
        Statement stmt = connection.createStatement();

        PreparedStatement prep = connection.prepareStatement("SELECT * FROM categories WHERE cat_id = ?");
        prep.setInt(1, catId);
        prep.execute();
        ResultSet rs = prep.getResultSet();

        while(rs.next()){
            System.out.println(rs.getString("category"));
        }
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

    public void readCategory(int catId) throws SQLException, ClassNotFoundException {

            this.catId = catId;

            Class.forName("org.sqlite.JDBC");
            Connection  connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement stmt = connection.createStatement();

            PreparedStatement prep = connection.prepareStatement(
                    "SELECT * FROM journal,categories WHERE journal.cat_id = categories.cat_id AND categories.cat_id = ? ");
            prep.setInt(1, catId);
            prep.execute();
            ResultSet rs = prep.getResultSet();
            while(rs.next()){
                System.out.println();
                System.out.println("\nCATEGORY: " + rs.getString("category")+"\n");
                System.out.println("Entry number: " + rs.getInt("entry_id") + "\n" + rs.getString("entry_date"));
                System.out.println();
                System.out.println(rs.getString("entry_content") + "\n");
                System.out.println(".....................................");
            }
        }


    public void printActions() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - to close\n" +
                "1  - see all categories\n" +
                "2  - to add a new category\n" +
                "3  - to modify a category\n" +
                "4  - to remove a category\n" +
                "5  - read a category\n"
        );

        System.out.println("\nWhat do you wish to do?");
    }

    public void catOptions() throws SQLException, ClassNotFoundException {
        String newCategory;
        boolean quit = false;
        while(!quit)
        {
            int action = scanCat.nextInt();
            scanCat.nextLine();
            switch (action)
            {
                case 0:
                    quit = true;
                    break;
                case 1:
                    seeCategories();
                    break;
                case 2:
                    System.out.println("Add a category...");
                    newCategory = scanCat.nextLine();
                    addCategory(newCategory);
                    break;
                case 3:
                    System.out.println("Type the category ID number you wish to update...");
                    catId = scanCat.nextInt();
                    int newCatId = catId;
                    getCategory(catId);
                    System.out.println("Do you wish to update it? Type 1. yes or 2. no");
                    int answer = scanCat.nextInt();
                    if (answer == 1)
                    {
                        updateCategory(newCatId);
                    }
                   catOptions();
                    break;
                case 4:
                    System.out.println("Type the category number you wish to delete...");
                    catId = scanCat.nextInt();
                    //getCategory(catId);
                    System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
                    answer = scanCat.nextInt();
                    if (answer == 1)
                    {
                       delCategory(catId);
                    }
                    delCategory(0);
                    break;
                case 5:
                    System.out.println("Type the entry number you wish to read...");
                    seeCategories();
                    catId = scanCat.nextInt();
                    readCategory(catId);
                    break;
            }
        }

    }
}
