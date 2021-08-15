package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import nl.marisabel.utils.connect;

public class Categories {

	private static final Scanner scanCat = new Scanner(System.in);
	private int catId = 0;
	connect c = new connect();

	public void addCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Add a category...");
		String newCategory = scanCat.nextLine();
		Statement statement = c.c().createStatement();
		statement.setQueryTimeout(30);

		PreparedStatement prep = c.c().prepareStatement("INSERT INTO categories (category) VALUES (?) ");
		prep.setString(1, newCategory);
		prep.execute();
		c.c().close();
		System.out.println(">>>>>>>> CATEGORY ADDED");

	}

	public void updateCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Type the category ID number you wish to update...");
		seeCategories();
		int catId = scanCat.nextInt();
		getCategory(catId);
		System.out.println("Do you wish to update it? Type 1. yes or 2. no");
		int answer = scanCat.nextInt();
		if (answer == 1) {
			System.out.println("Please update the category...");
			String newCategory = scanCat.next();

			Statement statement = c.c().createStatement();
			statement.setQueryTimeout(30);

			PreparedStatement prep = c.c().prepareStatement("UPDATE categories set category = ? WHERE cat_id = ?");
			prep.setString(1, newCategory);
			prep.setInt(2, catId);
			prep.execute();
			c.c().close();

			System.out.println(">>>>>>>> CATEGORY UPDATED");
		}
	}

	public void getCategory(int catId) throws SQLException, ClassNotFoundException {

		this.catId = catId;
		PreparedStatement prep = c.c().prepareStatement("SELECT * FROM categories WHERE cat_id = ?");
		prep.setInt(1, catId);
		prep.execute();
		ResultSet rs = prep.getResultSet();

		while (rs.next()) {
			System.out.println(rs.getString("category"));
		}

		c.c().close();
	}

	public void seeCategories() throws ClassNotFoundException, SQLException {
		Statement statement = c.c().createStatement();
		statement.setQueryTimeout(30);

		String query = "SELECT * FROM categories";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getInt("cat_Id") + ": " + rs.getString("category"));
		}
		c.c().close();
	}

	public void delCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Type the category number you wish to delete...");
		catId = scanCat.nextInt();
		getCategory(catId);
		System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
		int answer = scanCat.nextInt();
		if (answer == 1) {

			Statement statement = c.c().createStatement();
			statement.setQueryTimeout(30);
			PreparedStatement prep = c.c().prepareStatement("DELETE FROM categories WHERE cat_id = ?");
			prep.setInt(1, catId);
			prep.execute();
			c.c().close();
			System.out.println(">>>>>>>> CATEGORY DELETED");
		}
	}

	public void readCategory() throws SQLException, ClassNotFoundException {

		System.out.println("Type the entry number you wish to read...");
		seeCategories();
		catId = scanCat.nextInt();

		PreparedStatement prep = c.c().prepareStatement(
				"SELECT * FROM journal,categories WHERE journal.cat_id = categories.cat_id AND categories.cat_id = ? ");
		prep.setInt(1, catId);
		prep.execute();
		ResultSet rs = prep.getResultSet();
		while (rs.next()) {
			System.out.println();
			System.out.println("\nCATEGORY: " + rs.getString("category") + "\n");
			System.out.println("Entry number: " + rs.getInt("entry_id") + "\n" + rs.getString("entry_date"));
			System.out.println();
			System.out.println(rs.getString("entry_content") + "\n");
			System.out.println(".....................................");
		}
		c.c().close();
	}

	public void printActions() {
		System.out.println("\nOptions:\n");
		System.out.println("[0] to close\n" + "[1] see all categories\n" + "[2] to add a new category\n"
				+ "[3] to modify a category\n" + "[4] to remove a category\n" + "[5] read a category\n");

		System.out.println("\nWhat do you wish to do?");
	}

	public void catOptions() throws SQLException, ClassNotFoundException {
		boolean quit = false;
		while (!quit) {
			int action = scanCat.nextInt();
			scanCat.nextLine();
			switch (action) {
			case 0 -> quit = true;
			case 1 -> seeCategories();
			case 2 -> addCategory();
			case 3 -> updateCategory();
			case 4 -> delCategory();
			case 5 -> readCategory();
			}
		}

	}
}
