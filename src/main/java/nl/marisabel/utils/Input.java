package nl.marisabel.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

	static Scanner scanner = new Scanner(System.in);

	public static int getInt() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a number.");
			}

		}
	}


	public static Scanner scanner() {

		return scanner;
	}

}