import java.util.Scanner;

public class MainScreen {

    public static void Hello (){
    int option = 0;
    String name = null;
        System.out.println("\nWelcome back " + name + "!\n");
        System.out.println("What do you wish to do?");
        Scanner scan = new Scanner (System.in);
        System.out.println("Write the number of the option:\n#1 Read all past entries \n #2 Read one past entry  \n #3 Write new entry \n ");
        option = scan.nextInt();

        if(option == 1){}
        else if(option == 2){}
        else if(option == 3){}
        else {error;}

    }
}
