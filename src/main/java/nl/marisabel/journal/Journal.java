package nl.marisabel.journal;

import java.sql.SQLException;

import nl.marisabel.utils.Input;

public class Journal {

	public Journal() throws ClassNotFoundException, SQLException {
		// Load journal objects
		Entries entry = new Entries();
		ManageJournal newJournal = new ManageJournal();
		Categories cats = new Categories();

		// Open journal
		System.out.println();
		System.out.println("Journal is open.\n===================");
		options();
		boolean quit = false;
// TODO update menu to add password update journal options
		while (!quit) {
			int action = Input.getInt();
			switch (action) {
			case 0 -> {
				System.out.println("\nLocking...");
				quit = true;
			}
			case 1 -> entry.getEntries();
			case 2 -> entry.addEntry();
			case 3 -> entry.updateEntry();
			case 4 -> entry.deleteEntry();
			case 5 -> {
				cats.printActions();
				cats.catOptions();
			}
			case 6 -> entry.searchEntries();
			case 7 -> options();
			case 8 -> newJournal.createJournal();
			case 9 -> newJournal.delJournal();

			}
		}

	}

	private static void options() {
		System.out.println("OPTIONS:\n");
		System.out.println("[0] lock\n");
		System.out.println("[1] read journal \n" + "[2] new entry\n" + "[3] update entry\n" + "[4] remove entry\n"
				+ "[5] categories\n" + "[6] search entries\n" + "\n" + "[7] options\n" + "[8] create journal \n"
				+ "[9] delete journal");
		System.out.println("\nWhat do you wish to do? \nType the number of the option.");
	}
}
