package nl.marisabel.journal;

import java.sql.SQLException;
import java.util.Scanner;

public class Journal {

    private static Scanner scanner = new Scanner(System.in);

    public Journal() throws ClassNotFoundException, SQLException {

        Entries entry = new Entries();
        NewJournal newJournal = new NewJournal();
        Categories cats = new Categories();
        Search search = new Search();
        System.out.println();
        System.out.println("WELCOME TO JOURNAX!\ntext version\nBuilt JAN 2021\n♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ \n");
        printActions();
        int entryId;
        StringBuilder newEntry = new StringBuilder();
        boolean quit = false;
        while(!quit)
        {
            int action = Input.getInt();
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
                    newEntry = new StringBuilder(scanner.nextLine()+"_");
                    while(scanner.hasNext()) {
                       String newEntryP = scanner.nextLine();
                        if(newEntryP.equals("end")) break;
                        newEntry.append(newEntryP);
                    }

                    System.out.println("What's the category?\nSelect a number and press enter:");
                    cats.seeCategories();
                    int catId = scanner.nextInt();
                    entry.addEntry(Date.today(), newEntry.toString(), catId);
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
                    System.out.println("Search for a word...");
                    String searchWord = scanner.nextLine();
                    search.searchEntries(searchWord);
                    break;
                case 7:
                    printActions();
                    break;
                case 8:
                    newJournal.createJournal();
                    break;
                case 9:
                    newJournal.delJournal();
                    break;

            }
        }

    }


    private static void printActions() {
        System.out.println("OPTIONS:\n");
        System.out.println("0  - ❌ close\n" +
                "1  - 📖 read journal \n" +
                "2  - ➕ new entry\n" +
                "3  - ✒ update entry\n" +
                "4  - ♻ remove entry\n" +
                "5  - CATEGORIES\n" +
                "6  - 🔎 search entries\n" +
                "\n"+
                "7  - options\n" +
                "8  - 🔨 create journal \n" +
                "9 - ⚠ delete journal");
        System.out.println("\nWhat do you wish to do? \nType the number of the option.");
    }
}

