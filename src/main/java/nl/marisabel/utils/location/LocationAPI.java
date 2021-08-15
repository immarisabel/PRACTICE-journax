
package nl.marisabel.utils.location;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.Generated;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

@Generated("jsonschema2pojo")
public class LocationAPI {

	public String location() throws IOException, InterruptedException, JsonSyntaxException, MalformedJsonException {

		// API REQUEST

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://ip-geo-location.p.rapidapi.com/ip/check?format=json"))
				.header("x-rapidapi-key", "e0c5062070msh5a3786f34a7a33bp1afa72jsn38607315c044")
				.header("x-rapidapi-host", "ip-geo-location.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		String location = response.body();
		return location;


	}

}
