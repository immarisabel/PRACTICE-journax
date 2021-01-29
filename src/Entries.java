import java.util.Scanner;

public class Entries {


    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean quit = false;
        startJournal();
        printActions();
        while(!quit) {
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

//            switch (action) {
//                case 0:
//                    System.out.println("\nShutting down...");
//                    quit = true;
//                    break;
//
//                case 1:
//                    entries.printEntries();
//                    break;
//
//                case 2:
//                    addNewEntry();
//                    break;
//
//                case 3:
//                    updateEntry();
//                    break;
//
//                case 4:
//                    removeEntry();
//                    break;
//
//                case 5:
//                    queryEntry();
//                    break;
//
//                case 6:
//                    printActions();
//                    break;
//            }


        }

    }

    private static void addNewEntry() {
        System.out.println("Enter new entry: ");
        Date journalDate = new Date();
        System.out.println("\n" + journalDate.today());
        String name = scanner.nextLine();
        System.out.println("Enter Tags: ");
        String phone = scanner.nextLine();



    }


    private static void queryEntry() {
           }


    private static void startJournal() {
        System.out.println("Loading Journal...");
    }

    private static void printActions() {
        System.out.println("\nAvailable options:\npress");
        System.out.println("0  - to close\n" +
                "1  - to read all entries\n" +
                "2  - to add a new entry\n" +
                "3  - to update an existing entry\n" +
                "4  - to remove an existing entry\n" +
                "5  - search for tags\n" +
                "6  - to print options.");
        System.out.println("Choose your action: ");
    }
}
