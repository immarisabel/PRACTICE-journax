package nl.marisabel.journal;

import java.sql.SQLException;
import java.util.Scanner;

public class Home {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new FrontPage();

		Login login = new Login();
		login.getLogin();
		Scanner scan = new Scanner(System.in);

		boolean correct = false;

		System.out.println("current pw: " + login.getPw());

		if (login.getCountLogin() < 1) {

			login.setLogin();
		}

		while (!correct) {
			login.getLogin();
			String pw = login.getPw();
			String reminder = login.getReminder();
			System.out.println("\nWhat's the password?");
			String askPW = scan.nextLine();
			if (askPW.equals(pw)) {
				new Journal();
			}

			System.out.println("\nSorry, try again.\n");
			System.out.println("Your reminder: " + reminder);

		}
		scan.close();
	}

}
