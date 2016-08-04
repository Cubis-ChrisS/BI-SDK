package entities.bo;

import java.net.MalformedURLException;
import java.net.URL;

import rest.http.ConnectionRequest;

public class WebiReport extends InfoStore  {
	
	private URL webiURL;
	private String method;
	

	public WebiReport(String baseURL, int id) {
		// TODO Auto-generated constructor stub
		super(baseURL);
		String url  = this.setWebiURL(id);
	}
	
	public String setWebiURL(int id) {
		String urlString;
		urlString = raylightURL + "/documents/" + id;
		return urlString;
	}
	
	public String getWebiURL(String logonToken, int id) {
		
		method = "GET";
		String url = this.setWebiURL(id);
		try {
			webiURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String requestResponse = new ConnectionRequest(webiURL, method, logonToken).getResponse();
		return requestResponse;
		
	}
	
	
}
