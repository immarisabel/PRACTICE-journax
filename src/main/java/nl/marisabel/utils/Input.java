package nl.marisabel.utils;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
        }
    }


}