package entities.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import rest.http.ConnectionRequest;

public class Folders extends InfoStore  {
	
	private URL folderURL;
	private String method;
	
	public Folders(String baseURL) {
		// TODO Auto-generated constructor stub
		super(baseURL);
		
	}
	
	public String setFolderURL(int id) {
		String urlString;
		urlString = infoStoreURL + "/" + id;
		return urlString;
	}
	
	public URL getFolderURL() {
		return folderURL;
	}
	
	public String getFolderRoot(String logonToken) {
		
		method = "GET";
		String url = this.setFolderURL(23);
		try {
			folderURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String requestResponse = new ConnectionRequest(folderURL, method, logonToken).getResponse();
		return requestResponse;
		
	}
	
	
	public String getFolderChildren(String logonToken, int id) {
		
		method = "GET";
		String url = this.setFolderURL(id);
		url = url + "/children?/type=folder";
		
		try {
			folderURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String requestResponse = new ConnectionRequest(folderURL, method, logonToken).getResponse();
		return requestResponse;

	}
	
	public String getFolderMetaData(String logonToken, String uri) {
		
		method = "GET";
		String url = uri;
		
		try {
			folderURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String requestResponse = new ConnectionRequest(folderURL, method, logonToken).getResponse();
		return requestResponse;
	}

}
