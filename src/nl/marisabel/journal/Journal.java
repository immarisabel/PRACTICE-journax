package nl.marisabel.journal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Journal {

    private static Scanner scanner = new Scanner(System.in);
    public Journal() throws ClassNotFoundException {
        SQLite journal = new SQLite("","","");
        System.out.println("WELCOME TO JOURNAX! The text edition \nBuilt JAN 2021\n♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ \n");
        printActions();
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
                    journal.addEntry("Title Two",today(),"blah blah blah");
                    break;
                case 3:
                    journal.updateEntry(2,"title 2 updated","text new here, date should still be 29 Jan.");
                    break;
                case 4:
                    //TODO create a loop where if chosen, you write and after, you press another number to save.
                    journal.deleteEntry(1);
                    break;
                case 5:
                    //TODO search
                    break;
                case 6:
                    printActions();
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
                "6  - to see options again");
        System.out.println("\nWhat do you wish to do?");
    }
}
