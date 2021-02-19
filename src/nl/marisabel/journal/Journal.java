package nl.marisabel.journal;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Journal {


    //TODO create a cancel option per case (specially DEL + MOD)

    private static Scanner scanner = new Scanner(System.in);
    public Journal() throws ClassNotFoundException, SQLException {
        ReadEntry journal = new ReadEntry("","");
        AddEntry add = new AddEntry("","");
        DelEntry delete = new DelEntry("","");
        ModEntry modify = new ModEntry("","");
        NewJournal newJournal = new NewJournal();

        System.out.println("WELCOME TO JOURNAX!\nThe text edition\nBuilt JAN 2021\n♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ \n");
        printActions();
        int entryId;
        String entry ;
        boolean quit = false;
        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("\nClosing...");
                    quit = true;
                    break;
                case 1:
                    System.out.println(">>>>> ALL ENTRIES:\n");
                    journal.getEntries();
                    break;
                case 2:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    System.out.println("Dear diary...");
                    entry = scanner.nextLine();
                   add.addEntry(today(),entry);
                    break;
                case 3:
                    System.out.println("Type the entry number you wish to update...");
                    entryId = scanner.nextInt();
                    int entryIdUpd = entryId;
                    journal.getEntry(entryId);
                    System.out.println("Do you wish to update it? Type 1. yes or 2. no");
                    int answer = scanner.nextInt();
                    if (answer == 1)
                    {
                        modify.updateEntry(entryIdUpd);

                    }
                    break;

                case 4:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    System.out.println("Type the entry number you wish to delete...");
                    entryId = scanner.nextInt();
                    journal.getEntry(entryId);
                    System.out.println("Do you wish to delete it? Type 1. yes or 2. no");
                    answer = scanner.nextInt();
                    if (answer == 1)
                    {
                        delete.deleteEntry(entryId);

                    }
                    break;
                case 5:
                    //TODO search
                    break;
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





    /*date GENERATOR*/

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));

    }


    private static void printActions() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - to close\n" +
                "1  - to read all entries\n" +
                "2  - to add a new entry\n" +
                "3  - to update an existing entry\n" +
                "4  - to remove an existing entry\n" +
                "5  - search for tags\n" +
                "6  - to see options again\n" +
                "7  - create journal \n" +
                "8  - delete");
        System.out.println("\nWhat do you wish to do?");
    }
}

