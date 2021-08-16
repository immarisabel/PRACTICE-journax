package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import nl.marisabel.utils.Date;
import nl.marisabel.utils.Input;
import nl.marisabel.utils.connect;

public class Entries {

	private String newEntry;
	private int entryId = 0;

	Scanner scan = new Scanner(System.in); // from input.java does not work for string
	Categories cats = new Categories();
	connect c = new connect();

// Add new journal entry
	public void addEntry() throws ClassNotFoundException, SQLException {

		System.out.println("Dear diary...");
		newEntry = scan.nextLine();
		while (scan.hasNext()) {
			String newEntryP = scan.nextLine();
			// Continue taking paragraphs until the word "end" is input
			if (newEntryP.equals("end"))
				break;
			newEntry = (newEntry + "\n" + newEntryP);
		}

		System.out.println("What's the category?\nSelect a number and press enter:");
		// show categories
		cats.seeCategories();
		// ask for category number ID
		int catId = Input.getInt();
		// insert entry in database
		PreparedStatement prep = c.c()
				.prepareStatement("INSERT INTO journal (entry_content, entry_date, cat_id) VALUES (?,?,?)");
		prep.setString(1, newEntry);
		prep.setString(2, Date.today());
		prep.setInt(3, catId);
		prep.execute();
		// confirm succesful entry
		System.out.println(">>>>>>>> ENTRY ADDED");
		c.c().close();
	}
// Update existing entry
	public void updateEntry() throws ClassNotFoundException, SQLException {
		// request entry ID number
		System.out.println("Type the entry number you wish to update...");
		// scan entry number
		entryId = Input.getInt();
		// request entry id from database
		getEntry(entryId);
		// confirm
		System.out.println("Do you wish to update it?\n[1] yes\n[2] no");
		int answer = Input.getInt();
		// create a new entry loop if yes
		if (answer == 1) {
			// get new entry
			// TODO can I get the original entry to load and edit?
			// Or is it impossible on the console?
			System.out.println("Please update your entry...");
			while (scan.hasNext()) {
				String newEntryP = scan.nextLine();
				// Continue taking paragraphs until the word "end" is input
				if (newEntryP.equals("end"))
					break;
				newEntry = (newEntry + "\n" + newEntryP);
			}
			// Insert updated entry to database
			PreparedStatement prep = c.c().prepareStatement("UPDATE journal set entry_content= ? WHERE entry_id = ?");
			prep.setString(1, newEntry);
			prep.setInt(2, entryId);
			prep.execute();
			// confirm update was succesful
			System.out.println(">>>>>>>> ENTRY UPDATED");
			c.c().close();
		}

	}
// read all entries
	public void getEntries() throws ClassNotFoundException, SQLException {

		System.out.println("ALL ENTRIES");
		System.out.println("======================================");

		String query = "SELECT * FROM journal LEFT JOIN categories WHERE journal.cat_id = categories.cat_id";
		ResultSet rs = c.s().executeQuery(query);
		while (rs.next()) {
			System.out.println();
			System.out.println("[ #" + rs.getInt("entry_id")+"  |  "+rs.getString("entry_date")+" | " +rs.getString("category")+ " ]");
			System.out.println();
			System.out.println(rs.getString("entry_content"));
			System.out.println(".....................................");
		}
		c.c().close();

	}
// delete selected entry
	public void deleteEntry() throws ClassNotFoundException, SQLException {
		// request entry ID
		System.out.println("Type the entry number you wish to delete...");
		entryId = Input.getInt();
		// Load entry ID
		getEntry(entryId);
		// confirm deletion
		System.out.println("Do you wish to delete it?\nCannot be undo!!!\n[1] yes\n[2] no");
		int answer = Input.getInt();
		// proceed with deletion
		if (answer == 1) {
		PreparedStatement prep = c.c().prepareStatement("DELETE FROM journal WHERE entry_id = ?");
		prep.setInt(1, entryId);
		prep.execute();
		System.out.println(">>>>>>>> ENTRY DELETED");
		c.c().close();
		}
		System.out.println("Deletion aborted.");
	}
// search for a word
    public void searchEntries( ) throws ClassNotFoundException, SQLException {
    	System.out.println("Search for a word...");

		String searchWord = scan.nextLine();
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
		System.out.println("\nNothing found.");
        c.c().close();

    }
// get entry matching ID in order to update or delete
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

}
