package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import nl.marisabel.utils.Input;
import nl.marisabel.utils.connect;

public class Categories {

	private int catId = 0;
	connect c = new connect();
	Scanner scan = new Scanner(System.in);
// create new category
	public void addCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Add a category...");
		String newCategory = scan.nextLine();

		PreparedStatement prep = c.c().prepareStatement("INSERT INTO categories (category) VALUES (?) ");
		prep.setString(1, newCategory);
		prep.execute();
		c.c().close();
		System.out.println(">>>>>>>> CATEGORY ADDED");
	}

// update existing category
	public void updateCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Type the category ID number you wish to update...");
		seeCategories();
		int catId = Input.getInt();
		getCategory(catId);
		System.out.println("Do you wish to update it?\n[1] yes\n[2] no");
		int answer = Input.getInt();
		if (answer == 1) {
			System.out.println("Please update the category...");
			String newCategory = scan.nextLine();
			PreparedStatement prep = c.c().prepareStatement("UPDATE categories set category = ? WHERE cat_id = ?");
			prep.setString(1, newCategory);
			prep.setInt(2, catId);
			prep.execute();
			c.c().close();

			System.out.println(">>>>>>>> CATEGORY UPDATED");
		}
	}

// call category name for entries
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

// display all available categories
	public void seeCategories() throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM categories";

		ResultSet rs = c.s().executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getInt("cat_Id") + ": " + rs.getString("category"));
		}
		c.c().close();
	}

// delete a category
	// TODO what happens to entries with deleted categories?
	// TODO how to display these uncatgorized entries to be updated or deleted?
	// TODO how to delete a whole set of entries in one cat
	public void delCategory() throws ClassNotFoundException, SQLException {

		System.out.println("Type the category number you wish to delete...");
		catId = Input.getInt();
		getCategory(catId);
		System.out.println("Do you wish to delete it?/n[1] yes [2] no");
		int answer = Input.getInt();
		if (answer == 1) {
			PreparedStatement prep = c.c().prepareStatement("DELETE FROM categories WHERE cat_id = ?");
			prep.setInt(1, catId);
			prep.execute();
			c.c().close();
			System.out.println(">>>>>>>> CATEGORY DELETED");
		}
	}

// read all entries in a category
	public void readCategory() throws SQLException, ClassNotFoundException {
		System.out.println("Type the entry number you wish to read...");
		seeCategories();
		catId = Input.getInt();
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

// Category menu
	public void printActions() {
		System.out.println("\nOptions:\n");
		System.out.println("[0] to close\n" + "[1] see all categories\n" + "[2] to add a new category\n"
				+ "[3] to modify a category\n" + "[4] to remove a category\n" + "[5] read a category\n");

		System.out.println("\nWhat do you wish to do?");
	}

	public void catOptions() throws SQLException, ClassNotFoundException {
		boolean quit = false;
		while (!quit) {
			int action = Input.getInt();
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
