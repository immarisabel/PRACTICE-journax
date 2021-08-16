package nl.marisabel.journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import nl.marisabel.utils.connect;

public class Login {

	String pw = "";
	String reminder = "";
	String username = "";
	Scanner scan = new Scanner(System.in);
	connect c = new connect();

	public void setLogin() throws ClassNotFoundException, SQLException {
		// request new information for journal
		System.out.println("No user found.");
		System.out.println("How should I call you?");
		username = scan.nextLine();
		System.out.println("Create new password");
		pw = scan.nextLine();
		System.out.println("Write a reminder. Just in case...");
		reminder = scan.nextLine();
		// add information to database
		PreparedStatement prep = c.c()
				.prepareStatement("INSERT INTO login (password, reminder, username) VALUES (?,?,?)");
		prep.setString(1, pw);
		prep.setString(2, reminder);
		prep.setString(3, username);
		prep.execute();
		c.c().close();

		getLogin();
	}

	public void getLogin() throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM login";
		ResultSet rs = c.s().executeQuery(query);
		while (rs.next()) {
			this.pw = rs.getString("password");
			this.reminder = rs.getString("reminder");
			this.username = rs.getString("username");
		}
		c.c().close();
	}

	public int checkIfLoginInfo() throws SQLException, ClassNotFoundException {
		// this query will check that there is only 1 row on the database
		String query = "select 1 from login limit 1";
		ResultSet rs = c.s().executeQuery(query);
		int rowCount = 0;
		while(rs.next()) {
		    // Increment rowCount by 1
		    rowCount++;
		    // Process the result set data for the current row
		}
		return rowCount;
	}

	public String getPw() {
		return pw;
	}

	public String getReminder() {
		return reminder;
	}

	public String getName() {
		return username;
	}

}
