package nl.marisabel.journal;

import java.sql.SQLException;
import java.util.Scanner;

public class Journal {

	private static Scanner scanner = new Scanner(System.in);

	public Journal() throws ClassNotFoundException, SQLException {

		Entries entry = new Entries();
		NewJournal newJournal = new NewJournal();
		Categories cats = new Categories();
		System.out.println();
		System.out.println("Journal is open.\n===================");
		printActions();
		boolean quit = false;

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
			case 7 -> printActions();
			case 8 -> newJournal.createJournal();
			case 9 -> newJournal.delJournal();

			}
		}

	}

	private static void printActions() {
		System.out.println("OPTIONS:\n");
		System.out.println("[0] lock\n");
		System.out.println("[1] read journal \n" + "[2] new entry\n" + "[3] update entry\n" + "[4] remove entry\n"
				+ "[5] categories\n" + "[6] search entries\n" + "\n" + "[7] options\n" + "[8] create journal \n"
				+ "[9] delete journal");
		System.out.println("\nWhat do you wish to do? \nType the number of the option.");
	}
}
