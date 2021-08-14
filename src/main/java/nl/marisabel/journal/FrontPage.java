package nl.marisabel.journal;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FrontPage {
	connect c = new connect();
	Location l = new Location();
	String name = "Mari";
	int eCount = 0;
	String catName = "cat one";
	int cCount = 3;
	String lastEntryDate = "date";

	public void countEntries() throws ClassNotFoundException, SQLException {
		Statement stmt = c.c().createStatement();
		String query = "select * from journal";
		ResultSet rs = stmt.executeQuery(query);
		int eCount = 0;
		while (rs.next()) {
			eCount++;
		}
		this.eCount = eCount;
	}

	public void countCatEntries() {

		// get categories
		// create queries where each cat entries are count
		// multidemnsional arrayList
		// cat ID - cat name - cat count

	}

	public FrontPage() throws ClassNotFoundException, SQLException, UnknownHostException {

		// count entries total
		countEntries();
		// generate location from google's API
		try {
			String location = l.location();

			System.out.printf("Hello %s!\nWelcome to Journax!", name);
			System.out.println("\n");
			System.out.println("Date: " + Date.today());
			System.out.println("Location: " + location);
			System.out.println("Weather: Sunny 20C");

			System.out.println("=================\n");
			System.out.println("Journal Summary:\n");
			System.out.println("Entries: " + eCount);
			System.out.println("\nLast entry: " + lastEntryDate);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}