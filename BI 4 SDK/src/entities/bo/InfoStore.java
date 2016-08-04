package entities.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import rest.http.ConnectionRequest;

public class InfoStore {
	
	protected String urlIs;
	protected String urlRl;
	protected URL infoStoreURL;
	protected URL raylightURL;
	
	public InfoStore () {
		
	}
	
	public InfoStore (String baseURL) {
		

		urlIs = setIsURL(baseURL);
		urlRl = setRaylightURL(baseURL);
	    try {
			infoStoreURL = new URL(urlIs);
			raylightURL = new URL(urlRl);
	    } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String getInfoStore(String logonToken) {
		
		String method = "GET";
		String requestResponse = new ConnectionRequest(infoStoreURL, method, logonToken).getResponse();
		return requestResponse;
		
	}
		
	    
		public String setIsURL(String url) {
			url = url + "/infostore";
			return url;
		}
		
		public String setRaylightURL(String url) {
			url = url + "/raylight/v1";
			return url;
		}
		
		public URL getIsURL() {
			return infoStoreURL;
		}
		
		public URL getRlURL() {
			return raylightURL;
		}
		
	}
	

