package nl.marisabel.journal;

import java.sql.SQLException;
import java.util.Scanner;

public class Journal {

    private static Scanner scanner = new Scanner(System.in);

    public Journal() throws ClassNotFoundException, SQLException {

        Entries entry = new Entries();
        NewJournal newJournal = new NewJournal();
        Categories cats = new Categories();

        System.out.println("WELCOME TO JOURNAX!\nThe text edition\nBuilt JAN 2021\n♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ \n");
        printActions();
        int entryId;
        String newEntry ;
        boolean quit = false;
        while(!quit)
        {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action)
            {
                case 0:
                    System.out.println("\nClosing...");
                    quit = true;
                    break;
                case 1:
                    System.out.println(">>>>> ALL ENTRIES:\n");
                    entry.getEntries();
                    printActions();

                    break;
                case 2:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    System.out.println("Dear diary...");
                    newEntry = scanner.nextLine();
                    System.out.println("What's the category?\nSelect a number and press enter:");
                    cats.seeCategories();
                    int catId = scanner.nextInt();
                    entry.addEntry(Date.today(),newEntry, catId);

                    printActions();

                    break;
                case 3:
                    System.out.println("Type the entry number you wish to update...");
                    entryId = scanner.nextInt();
                    int entryIdUpd = entryId;
                    entry.getEntry(entryId);
                    System.out.println("Do you wish to update it? Type 1. yes or 2. no");
                    int answer = scanner.nextInt();
                    if (answer == 1)
                    {
                        entry.updateEntry(entryIdUpd);
                    }
                    printActions();

                    break;

                case 4:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    System.out.println("Type the entry number you wish to delete...");
                    entryId = scanner.nextInt();
                    entry.getEntry(entryId);
                    System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
                    answer = scanner.nextInt();
                    if (answer == 1)
                    {

                        entry.deleteEntry(entryId);

                    }
                    printActions();

                    break;
                case 5:
                    //TODO move
                    cats.printActions();
                    cats.catOptions();
                case 6:
                    printActions();
                    break;
                case 7:
                    newJournal.createJournal();
                    break;
                case 8:
                    newJournal.delJournal();
                    break;

            }
        }

    }


    private static void printActions() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - to close\n" +
                "1  - to read all entries\n" +
                "2  - to add a new entry\n" +
                "3  - to update an existing entry\n" +
                "4  - to remove an existing entry\n" +
                "5  - see categories\n" +
                "6  - to see options again\n" +
                "7  - create journal \n" +
                "8  - delete");
        System.out.println("\nWhat do you wish to do?");
    }
}

