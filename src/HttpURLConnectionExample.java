

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public  class HttpURLConnectionExample {

		
		
		
	
	public CustomSearch sendGet(String q) throws Exception {
		
		String field = "&fields=items(title,link)";
		String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyBqBB6y9iLfdRW4Re1XBEtR2RM6l2j329E&cx=009765904947196158732:qtjje0lqrya&q=" + 
		q + field;
		Gson gson = new Gson();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return gson.fromJson(response.toString(), CustomSearch.class);

		

	}
}


