package nl.marisabel.journal;

import java.net.InetAddress;
import java.net.UnknownHostException;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;

public class Location {

	@SuppressWarnings("static-access")
	public void ip() throws UnknownHostException {
		
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress.getLocalHost());
		// return String.valueOf(inetAddress);

	}
	
	public Location() throws UnknownHostException {
		IPGeolocationAPI api = new IPGeolocationAPI("fe81355e74fa4346bb0bbc90a0612f73");

	
	// Get geolocation for IP address (1.1.1.1) and fields (geo, time_zone and currency)
	GeolocationParams geoParams = new GeolocationParams();
	geoParams.setIPAddress("");
	geoParams.setFields("geo,time_zone,currency");
	Geolocation geolocation = api.getGeolocation(geoParams);
//
//	// Check if geolocation lookup was successful
//	if(geolocation.getStatus() == 200) {
//	    System.out.println(geolocation.getCountryName());
//	    System.out.println(geolocation.getCurrency().getName());
//	    System.out.println(geolocation.getTimezone().getCurrentTime());
//	} else {
//	    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
//	}
//
//	// Get geolocation in Russian** for IP address (1.1.1.1) and all fields
//	GeolocationParams geoParams1 = new GeolocationParams();
//	geoParams.setIPAddress("1.1.1.1");
//	geoParams.setLang("ru");
//
//	Geolocation geolocation1 = api.getGeolocation(geoParams);
//
//	// Check if geolocation lookup was successful
//	if(geolocation.getStatus() == 200) {
//	    System.out.println(geolocation.getIPAddress());
//	    System.out.println(geolocation.getCountryName());
//	} else {
//	    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
//	}

	// Get geolocation for the calling machine's IP address for all fields
//	Geolocation geolocation1 = api.getGeolocation();
//
//	if(geolocation.getStatus() == 200) {
//	    System.out.println(geolocation.getCountryCode2());
//	    System.out.println(geolocation.getTimezone().getCurrentTime());
//	} else {
//	    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
//	}

	}
}
