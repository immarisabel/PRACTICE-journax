package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Entries {

	private static final Scanner scanner = new Scanner(System.in);
	private String newEntry;
	private int entryId = 0;
	Categories cats = new Categories();
	connect c = new connect();

	public void addEntry() throws ClassNotFoundException, SQLException {

		System.out.println("Dear diary...");
		newEntry = scanner.nextLine();
		while (scanner.hasNext()) {
			String newEntryP = scanner.nextLine();
			if (newEntryP.equals("end"))
				break;
			newEntry = (newEntry + "\n" + newEntryP);
		}

		System.out.println("What's the category?\nSelect a number and press enter:");
		cats.seeCategories();
		int catId = scanner.nextInt();

		PreparedStatement prep = c.c()
				.prepareStatement("INSERT INTO journal (entry_content, entry_date, cat_id) VALUES (?,?,?)");
		prep.setString(1, newEntry);
		prep.setString(2, Date.today());
		prep.setInt(3, catId);
		prep.execute();

		System.out.println(">>>>>>>> ENTRY ADDED");
		c.c().close();
	}

	public void updateEntry() throws ClassNotFoundException, SQLException {

		System.out.println("Type the entry number you wish to update...");
		entryId = scanner.nextInt();
		getEntry(entryId);
		System.out.println("Do you wish to update it? Type 1. yes or 2. no");
		int answer = scanner.nextInt();
		if (answer == 1) {

			Statement statement = c.c().createStatement();
			statement.setQueryTimeout(30);

			System.out.println("Please update your entry...");
			String newEntry = scanner.nextLine();

			PreparedStatement prep = c.c()
					.prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?"); /* TIME FORMAT! */
			prep.setString(1, newEntry);
			prep.setInt(2, entryId);
			prep.execute();

			System.out.println(">>>>>>>> ENTRY UPDATED");
			c.c().close();
		}

	}

	public void getEntry(int entryId) throws SQLException, ClassNotFoundException {

		this.entryId = entryId;

		PreparedStatement prep = c.c().prepareStatement("SELECT * FROM journal WHERE entry_id = ?");
		prep.setInt(1, entryId);
		prep.execute();
		ResultSet rs = prep.getResultSet();

		while (rs.next()) {
			System.out.println(rs.getString("entry_content"));
		}
		c.c().close();
	}

	public void getEntries() throws ClassNotFoundException, SQLException {

		System.out.println("ALL ENTRIES");
		System.out.println("======================================");

		Statement statement = c.c().createStatement();
		statement.setQueryTimeout(30);
		String query = "SELECT * FROM journal LEFT JOIN categories WHERE journal.cat_id = categories.cat_id";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			System.out.println();
			System.out.println("[ #" + rs.getInt("entry_id")+"  |  "+rs.getString("entry_date")+" | " +rs.getString("category")+ " ]");
			System.out.println();
			System.out.println(rs.getString("entry_content"));
			System.out.println(".....................................");
		}
		c.c().close();

	}

	public void deleteEntry() throws ClassNotFoundException, SQLException {
		
		System.out.println("Type the entry number you wish to delete...");
		entryId = scanner.nextInt();
		getEntry(entryId);
		System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
		int answer = scanner.nextInt();
		if (answer == 1) {
		
		PreparedStatement prep = c.c().prepareStatement("DELETE FROM journal WHERE entry_id = ?");
		prep.setInt(1, entryId);
		prep.execute();

		System.out.println(">>>>>>>> ENTRY DELETED");
		c.c().close();
		}

	}
	
    public void searchEntries( ) throws ClassNotFoundException, SQLException {
    	System.out.println("Search for a word...");
		String searchWord = scanner.nextLine();
    	
        Statement statement = c.c().createStatement();
        statement.setQueryTimeout(30);

        PreparedStatement prep = c.c().prepareStatement("SELECT * FROM journal WHERE entry_content LIKE ? ORDER BY entry_date ");
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
        c.c().close();

    }
    
}
