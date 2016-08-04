package rest.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;

public class ConnectionRequest {
	
	private String requestResponse;

	public ConnectionRequest(URL url, String method) {
		// TODO Auto-generated constructor stub
		requestResponse = this.setConnectionRequest(url, method);
	}
	
	public ConnectionRequest(URL url, String method, JSONObject jsonObj) {
		requestResponse = this.setConnectionRequest(url, method, jsonObj);
	}
	
	public ConnectionRequest(URL url, String method, String token) {
		requestResponse = this.setConnectionRequest(url, method, token);
	}
	
	public ConnectionRequest(URL url, String method, String token, JSONObject jsonObj) {
		requestResponse = this.setConnectionRequest(url, method, token, jsonObj);
	}
	
	public String getResponse() {
		return requestResponse;
	}
	
	public String setConnectionRequest(URL url, String method, String logonToken) {
		
		HttpURLConnection conn = null;
        BufferedReader br = null;
        String output = null;
    	StringBuilder sb = new StringBuilder();
    	
    	//lib java.net.HttpURLConnection
        //open the connection
        //-----------------------------------------------
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//************************************************
		
		// set method of URL request
		//------------------------------------------------
        try {
			conn.setRequestMethod(method);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //************************************************
        
        //Methods inherited from class java.net.URLConnection
        //Set property for http header
        //------------------------------------------------
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("X-SAP-LogonToken", logonToken);

        try {
			if (conn.getResponseCode() != 200) {

			    throw new RuntimeException("Failed : HTTP error code : "
			            + conn.getResponseCode());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //*************************************************
        
        //lib java.io.BufferedReader
        //Read StreamReader into buffer
        //-------------------------------------------------
		try {
			br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*************************************************

        //StringBuilder output;
		//-------------------------------------------------
        try {

			while ((output = br.readLine()) != null) {
			    //System.out.println(output);
				sb.append(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //*************************************************
        
        //Disconnect the connection
    	conn.disconnect();
    	return sb.toString();
		
	}
	
	public String setConnectionRequest(URL url, String method) {
		
		 HttpURLConnection conn = null;
	        BufferedReader br = null;
	        String output = null;
	    	StringBuilder sb = new StringBuilder();
	        
	        // lib java.net.HttpURLConnection
	        // open the connection
	        //-----------------------------------------------
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//************************************************
			
			// set method of URL request
			//------------------------------------------------
	        try {
				conn.setRequestMethod(method);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //************************************************
	        
	        //Methods inherited from class java.net.URLConnection
	        //Set property for http header
	        //------------------------------------------------
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("charset", "utf-8");

	        try {
				if (conn.getResponseCode() != 200) {

				    throw new RuntimeException("Failed : HTTP error code : "
				            + conn.getResponseCode());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //lib java.io.BufferedReader
	        //Read StreamReader into buffer
	        //-------------------------------------------------
			try {
				br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//*************************************************

	        //StringBuilder output;
			//-------------------------------------------------
	        try {

				while ((output = br.readLine()) != null) {
				    //System.out.println(output);
					sb.append(output);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //Disconnect the connection
	     conn.disconnect();
		return sb.toString();
	}
	
	public String setConnectionRequest(URL url, String method, JSONObject jsonObj) {
		
		 HttpURLConnection conn = null;
	        BufferedReader br = null;
	        String output = null;
	    	StringBuilder sb = new StringBuilder();
	        
	        // lib java.net.HttpURLConnection
	        // open the connection
	        //-----------------------------------------------
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//************************************************
			
			// set method of URL request
			//------------------------------------------------
	        try {
				conn.setRequestMethod(method);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //************************************************
	        
	        //Methods inherited from class java.net.URLConnection
	        //Set property for http header
	        //------------------------------------------------
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("charset", "utf-8");
	        if (method.contentEquals("PUT") || method.contentEquals("POST")) {
	            conn.setRequestProperty("Content-Type", "application/json");           
	            conn.setDoOutput(true);
	            
	            OutputStreamWriter wr;
	    		try {
	    			wr = new OutputStreamWriter(conn.getOutputStream());
	    			wr.write(jsonObj.toString());
	    			wr.flush();
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	        }

	        try {
				if (conn.getResponseCode() != 200) {

				    throw new RuntimeException("Failed : HTTP error code : "
				            + conn.getResponseCode());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //lib java.io.BufferedReader
	        //Read StreamReader into buffer
	        //-------------------------------------------------
			try {
				br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//*************************************************

	        //StringBuilder output;
			//-------------------------------------------------
	        try {

				while ((output = br.readLine()) != null) {
				    //System.out.println(output);
					sb.append(output);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //Disconnect the connection
	     conn.disconnect();
		return sb.toString();
	}
	
	public String setConnectionRequest(URL url, String method, String logonToken, JSONObject jsonObj) {
		
		 HttpURLConnection conn = null;
	        BufferedReader br = null;
	        String output = null;
	    	StringBuilder sb = new StringBuilder();
	        
	        // lib java.net.HttpURLConnection
	        // open the connection
	        //-----------------------------------------------
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//************************************************
			
			// set method of URL request
			//------------------------------------------------
	        try {
				conn.setRequestMethod(method);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //************************************************
	        
	        //Methods inherited from class java.net.URLConnection
	        //Set property for http header
	        //------------------------------------------------
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("charset", "utf-8");
	        conn.setRequestProperty("X-SAP-LogonToken", logonToken);
	        if (method.contentEquals("PUT") || method.contentEquals("POST")) {
	            conn.setRequestProperty("Content-Type", "application/json");           
	            conn.setDoOutput(true);
	            
	            OutputStreamWriter wr;
	    		try {
	    			wr = new OutputStreamWriter(conn.getOutputStream());
	    			wr.write(jsonObj.toString());
	    			wr.flush();
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	        }

	        try {
				if (conn.getResponseCode() != 200) {

				    throw new RuntimeException("Failed : HTTP error code : "
				            + conn.getResponseCode());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //lib java.io.BufferedReader
	        //Read StreamReader into buffer
	        //-------------------------------------------------
			try {
				br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//*************************************************

	        //StringBuilder output;
			//-------------------------------------------------
	        try {

				while ((output = br.readLine()) != null) {
				    //System.out.println(output);
					sb.append(output);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //*************************************************
	        
	        //Disconnect the connection
	     conn.disconnect();
		return sb.toString();
	}


}
