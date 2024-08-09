package nl.marisabel.journal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import nl.marisabel.utils.weather.Weather;

public class Home {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, IOException {

		ManageJournal mj = new ManageJournal();
		mj.createJournal();

		// call login page
		Login login = new Login();
		login.getLogin();
		if (login.checkIfLoginInfo() < 1) {
			// if no password is found, set up password
			login.setLogin();
		}
		// set flag for while loop
		boolean correct = false;

		// call front page information
		new FrontPage();

		// ask for password input
		Scanner scan = new Scanner(System.in);

		// System.out.println("current pw: " + login.getPw());

		while (!correct) {
			// loads password and reminder from database
			login.getLogin();
			String name = login.getName();
			String pw = login.getPw();
			// request password
			System.out.printf("\nHello %s!\nWhat's the password?", name);
			String askPW = scan.nextLine();
			if (askPW.equals(pw)) {
				// load journal if password is correct
				new Journal();
			}

			System.out.println("\nSorry, try again.\n");
			System.out.println("Your reminder: " + login.getReminder());
			// TODO can I set this loop after 3 times fail?
		}
		scan.close();
	}

}
