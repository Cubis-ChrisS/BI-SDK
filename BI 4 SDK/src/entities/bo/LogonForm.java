package entities.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

import rest.http.ConnectionRequest;

public class LogonForm {
	
	private URL logonURL;
	private String url;
	
	public LogonForm (String baseURL) {		
		
		url = setURL(baseURL);
	    try {
			logonURL = new URL(url);
	    } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Get JSON output for LogonForm
	public String getLogon() {		
		String method = "GET";
		String requestResponse = new ConnectionRequest(logonURL, method).getResponse();
		return requestResponse;
	}
	
	public String getToken(JSONObject logon) {
		
		String method = "POST";
		String requestResponse = new ConnectionRequest(logonURL, method, logon).getResponse();
		return requestResponse;
	}
	
	public String setURL(String url) {
		url = url + "/logon/long";
		return url;
	}
	
}
