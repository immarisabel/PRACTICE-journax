package nl.marisabel.journal;

import java.sql.SQLException;
import java.util.Scanner;

public class Home {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// create login class
		// create table for login by password
		// initialize input by setting password & reminder
		// store pw in database
		// loop to check for pw. If OK, load new Journal();, if not, ask again.
		// offer reminder if wrong password
		
		
		
		String pw = "";
		pw = "test";
		String reminder = "";
		reminder ="this is my reminder";
		boolean correct = false;

		while (!correct) {

			System.out.println("\nWhat's the password?");
			Scanner scan = new Scanner(System.in);
			String askPW = scan.nextLine();

			if (askPW.equals(pw)) {
				new Journal();
			}

			System.out.println("\nSorry, try again.\n");
			System.out.println("Your reminder: " + reminder );
			
		}

	}			

}
