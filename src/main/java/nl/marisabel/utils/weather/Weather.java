package nl.marisabel.utils.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import nl.marisabel.utils.location.Location;

public class Weather {

	public String weather() throws IOException, InterruptedException {
		Location location = new Location();
		String uriString1 = "https://api.weatherapi.com/v1/current.json?key=";
		String key = "e0477b84612f4249b0c133415211608";
		String uriString2 = "&q=";
		String loc = "Rotterdam"; // to replace with API call for location
		// String loc = location.getCity().getName();
		String uriString3 = "&aqi=no";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uriString1 + key + uriString2 + loc + uriString3))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		String weather = response.body();
		return weather;
	}
}
