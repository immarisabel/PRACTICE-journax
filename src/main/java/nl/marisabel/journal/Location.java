package nl.marisabel.journal;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Location {


	@SuppressWarnings("rawtypes")
	public String location() throws IOException, InterruptedException, JsonSyntaxException {
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://ip-geo-location.p.rapidapi.com/ip/check?format=json"))
//				.header("x-rapidapi-key", "e0c5062070msh5a3786f34a7a33bp1afa72jsn38607315c044")
//				.header("x-rapidapi-host", "ip-geo-location.p.rapidapi.com")
//				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		// System.out.println(response.body());
//
//		String location = response.body();

		String location = "{\"ip\":\"84.81.240.178\",\"type\":\"IPv4\",\"location\":{\"latitude\":51.9336,\"longitude\":4.4888},\"postcode\":\"3034\",\"area\":{\"code\":\"NL-ZH\",\"geonameid\":2743698,\"name\":\"South Holland\"},\"asn\":{\"number\":1136,\"organisation\":\"KPN B.V.\"},\"city\":{\"geonameid\":2747891,\"name\":\"Rotterdam\",\"population\":598199},\"continent\":{\"geonameid\":6255148,\"name\":\"Europe\",\"code\":\"EU\"},\"country\":{\"geonameid\":2750405,\"name\":\"Netherlands\",\"code\":\"NL\",\"capital\":\"Amsterdam\",\"area_size\":\"41526.00 sq. km\",\"population\":17231017,\"phone_code\":\"31\",\"is_in_eu\":true,\"languages\":{\"fy\":\"West Frisian language\",\"nl\":\"Flemish\"},\"flag\":{\"file\":\"https://commons.wikimedia.org/wiki/Special:FilePath/Flag_of_the_Netherlands.svg\",\"emoji\":\"??\",\"unicode\":\"U+1F1F3 U+1F1F1\"}},\"currency\":{\"code\":\"EUR\",\"name\":\"Euro\"},\"security\":{\"is_tor\":false,\"is_proxy\":false,\"is_crawler\":false,\"is_thread\":false},\"time\":{\"timezone\":\"Europe/Amsterdam\",\"time\":\"2021-08-14 19:24:09 +0200\",\"gtm_offset\":7200},\"status\":\"success\"}";

		Gson gson = new Gson();
		// generates map from JSON string
		Map lm = gson.fromJson(location, Map.class);
		Map pm = gson.fromJson(lm.get("city").toString(), Map.class);
		String name = pm.get("name").toString();
		// Map am = gson.fromJson(lm.get("area").toString(), Map.class);
		String locationName = (name);
		return location;

	}



}
