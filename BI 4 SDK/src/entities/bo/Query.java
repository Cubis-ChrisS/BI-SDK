package entities.bo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import rest.http.ConnectionRequest;

public class Query {

	private URL cmsQuery;
	
	public Query(String baseURL) {
		// TODO Auto-generated constructor stub
		String url = setURL(baseURL);
	    try {
			cmsQuery = new URL(url);
	    } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String setURL(String url) {
		url = url + "/v1/cmsquery";
		return url;
	}
	
	public URL getURL() {
		return cmsQuery;
	}
	
	public String getQueryDef(String token) {
		
		String method = "POST";
		
		String requestResponse = new ConnectionRequest(cmsQuery, method, token).getResponse();
		return requestResponse;
	}

}
