package nl.marisabel.journal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

import nl.marisabel.utils.Date;
import nl.marisabel.utils.connect;
import nl.marisabel.utils.location.Location;
import nl.marisabel.utils.location.LocationAPI;

public class FrontPage {
	connect c = new connect();
	LocationAPI l = new LocationAPI();

	int eCount = 0;
	String catName = "cat example";
	int cCount = 3;
	String lastEntryDate = "date will go here";

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

// TODO how to count and divide all categories
	public void countCatEntries() {

		// get categories
		// create queries where each cat entries are count
		// multidemnsional arrayList
		// cat ID - cat name - cat count

	}

	public FrontPage() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// REAL LOCATION API

		// // LocationAPI location = new LocationAPI();
		// // String json = location.location().toString();

		// TEST JSON STRING
		String json = "{\"ip\":\"84.81.240.178\",\"type\":\"IPv4\",\"location\":{\"latitude\":51.9336,\"longitude\":4.4888},\"postcode\":\"3034\",\"area\":{\"code\":\"NL-ZH\",\"geonameid\":2743698,\"name\":\"South Holland\"},\"asn\":{\"number\":1136,\"organisation\":\"KPN B.V.\"},\"city\":{\"geonameid\":2747891,\"name\":\"Rotterdam\",\"population\":598199},\"continent\":{\"geonameid\":6255148,\"name\":\"Europe\",\"code\":\"EU\"},\"country\":{\"geonameid\":2750405,\"name\":\"Netherlands\",\"code\":\"NL\",\"capital\":\"Amsterdam\",\"area_size\":\"41526.00 sq. km\",\"population\":17231017,\"phone_code\":\"31\",\"is_in_eu\":true,\"languages\":{\"fy\":\"West Frisian language\",\"nl\":\"Flemish\"},\"flag\":{\"file\":\"https://commons.wikimedia.org/wiki/Special:FilePath/Flag_of_the_Netherlands.svg\",\"emoji\":\"??\",\"unicode\":\"U+1F1F3 U+1F1F1\"}},\"currency\":{\"code\":\"EUR\",\"name\":\"Euro\"},\"security\":{\"is_tor\":false,\"is_proxy\":false,\"is_crawler\":false,\"is_thread\":false},\"time\":{\"timezone\":\"Europe/Amsterdam\",\"time\":\"2021-08-14 19:24:09 +0200\",\"gtm_offset\":7200},\"status\":\"success\"}";

		Location location = new Gson().fromJson(json, Location.class);
		String city = location.getCity().getName();
		String country = location.getCountry().getName();

		// count entries total
		countEntries();
		// String location = l.getCity().getName().toString();
		System.out.println("\n");
		System.out.println("Date: " + Date.today());
		System.out.println("Location: " + city + ", " + country);
		System.out.println("Weather: Sunny 20C");

		System.out.println("=================\n");
		System.out.println("Journal Summary:\n");
		System.out.println("Entries: " + eCount);
		System.out.println("\nLast entry: " + lastEntryDate);
	}

}
