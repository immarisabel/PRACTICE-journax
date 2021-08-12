package nl.marisabel.journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {

	String pw = "";
	String reminder = "";
	Scanner scan = new Scanner(System.in);
	
	public Login() {

	}

	public void setLogin() throws ClassNotFoundException, SQLException {
		System.out.println("No user found.");
		System.out.println("Create new password");
		pw = scan.nextLine();
		System.out.println("Write a reminder. Just in case...");
		reminder = scan.nextLine();

		PreparedStatement prep = connect.c().prepareStatement("INSERT INTO login (password, reminder) VALUES (?,?)");
		prep.setString(1, pw);
		prep.setString(2, reminder);
		prep.execute();
		connect.c().close();
		
		getLogin();
	}

	public void getLogin() throws ClassNotFoundException, SQLException {

		Statement stmt = connect.c().createStatement();
		String query = "SELECT * FROM login";
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			
			this.pw = rs.getString("password");
			this.reminder = rs.getString("reminder");
		}
		
		connect.c().close();
		
		
	}
	
	public int getCountLogin() throws SQLException, ClassNotFoundException {
		Statement stmt = connect.c().createStatement();
		String query = "select 1 from login limit 1";
		ResultSet rs = stmt.executeQuery(query);
		
		
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

}
