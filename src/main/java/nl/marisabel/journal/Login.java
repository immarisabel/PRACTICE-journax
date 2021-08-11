package nl.marisabel.journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

	String pw = "";
	String reminder = "";

	public Login(String pw, String reminder) {
		super();
		this.pw = pw;
		this.reminder = reminder;
	}

	public String getPw() {

		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

}
