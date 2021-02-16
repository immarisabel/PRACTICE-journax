package nl.marisabel.journal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Journal {

    private static Scanner scanner = new Scanner(System.in);
    public Journal() throws ClassNotFoundException {
        ReadEntry journal = new ReadEntry("","");
        AddEntry add = new AddEntry("","");
        DelEntry delete = new DelEntry("","");
        ModEntry modify = new ModEntry("","");

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
                    System.out.println("Enter Entry ID number...");
                    entryId = scanner.nextInt();
                    System.out.println("This is the current entry:\n");
                    journal.getEntry(entryId, "Test from Journal.class");
//                    System.out.println("write it out!   >>");
//
//                    entry = scanner.nextLine();
//                    modify.updateEntry(entryId,entry);
                    break;

                case 4:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    System.out.println("Type the entry number you wish to delete...");
                    entryId = scanner.nextInt();
                    delete.deleteEntry(entryId);
                    break;
                case 5:
                    //TODO search
                    break;
                case 6:
                    printActions();
                    break;
                case 7:
                    journal.createJournal();
                    break;
                case 8:
                    journal.delJournal();
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
