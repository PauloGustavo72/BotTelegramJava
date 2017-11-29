

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public  class HttpURLConnectionExample {

		
		
		
	

	public CustomSearch sendGet(String q, String longitude, String latitude) throws Exception {
		
		//String field = "&fields=items(title,link)";
				String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ longitude +"," + latitude
						+ "&radius=500&"
						+ "keyword=" + q.replace(" " , "%20") 
						+ "&open_now=true&key=AIzaSyCB07ev1nkhSWCdATGSDgCBN-hLmaWJUFc"; 
				
				
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
				
				
				CustomSearch fromJson = gson.fromJson(response.toString(), CustomSearch.class);
				
				System.out.println(gson.toJson(fromJson));
				
				return fromJson;

		

	}
}


