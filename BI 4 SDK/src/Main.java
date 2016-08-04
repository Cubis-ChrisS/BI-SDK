// Generic Java Imports
import java.io.*;
import java.util.ArrayList;
import static java.lang.System.out;


import java.util.List;

// Apache http Components
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;

// URL Encoding
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

// These imports are for the XML DOM Parser %>
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.w3c.dom.Node.*;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.parsers.*;

import org.xml.sax.InputSource;


// These imports are for transforming the DOM Parser back into a string
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import rest.http.*;

public class Main {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		String redirectURL = "testRest.jsp";
		// Enterprise authentication credentials => Populated by xml
		String boUsername = "92788";
		String boPassword = "SAP_C_85P";
		String boAuthType = "secSAPR3";
		
		//Restful URL's
		final String baseURL = "http://XXSAPS23.stib-mivb.be:6405/biprws";
		final String logonURL = baseURL + "/logon/long";
		final String logoffURL = baseURL + "/logoff";
		String infoStoreQueryURL = baseURL + "/infostore/23";
		
		String xmlString = "";
		String logonToken = "";

		xmlString = restGet(logonURL,"","","",""); //Get the XML string for logging on	
		
		xmlString = addLogonInfoToXML(xmlString, boUsername, boPassword, boAuthType);
		
		String logonXML = restPost(logonURL, xmlString, "","","","");	
		logonToken = getLogonTokenFromXML(logonXML);
		
		
		
		xmlString = restGet(infoStoreQueryURL, "X-SAP-LogonToken",logonToken,"","");
		
		out.println(xmlString);

	}
	
		
	//Methods and Functions
		public static String restGet(String urlStr, String param1Name, String param1Value, String param2Name, String param2Value ) throws Exception {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
			  HttpGet httpget = new HttpGet(urlStr);
			  httpget.addHeader("Accept", "application/xml");
			  httpget.addHeader("Content-Type", "application/xml");
			 
			  if (!param1Name.equals("")) {
			   httpget.addHeader(param1Name, param1Value);
			  }
			  if (!param2Name.equals("")) {
			   httpget.addHeader(param2Name, param2Value);
			  }
			  
			  ResponseHandler<String> responseHandler = new BasicResponseHandler();
			  String responseBody = httpclient.execute(httpget, responseHandler);
			  return(responseBody);
			} finally {
			  // When HttpClient instance is no longer needed,
			        // shut down the connection manager to ensure
			        // immediate deallocation of all system resources
			        httpclient.close();
			}
			}
		
		public static String addLogonInfoToXML(String xmlString, String username, String password, String authType ) throws Exception {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(xmlString)));
			Document doc = parser.getDocument();
			NodeList nodes = doc.getElementsByTagName("attr");

			for (int i = 0; i < nodes.getLength(); i++) {
			  Element element = (Element) nodes.item(i);
			 
			  // Is this the correct XML token
			  if (element.getAttribute("name").equals("userName")) {
			   element.setTextContent(username);
			  }
			  // Is this the correct XML token
			  if (element.getAttribute("name").equals("password")) {
			   element.setTextContent(password);
			  }
			  // Is this the correct XML token
			  if (element.getAttribute("name").equals("auth")) {
			   element.setTextContent(authType);
			  }
			}
			return (convertDomToString(doc));
			}
		
		public static String convertDomToString(Document doc) throws Exception {
			// Now convert the document back to a string
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
			return(output);
			}
		
		public static String restPost(String urlStr, String XMLString, String param1Name, String param1Value, String param2Name, String param2Value ) throws Exception {	 

			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
			  HttpPost httpPost = new HttpPost(urlStr);
			  httpPost.addHeader("Accept", "application/xml");
			  httpPost.addHeader("Content-Type", "application/xml");
			 
			  if (!param1Name.equals("")) {
			   httpPost.addHeader(param1Name, param1Value);
			  }
			  if (!param2Name.equals("")) {
			   httpPost.addHeader(param2Name, param2Value);
			  }
			 
			  httpPost.setEntity(new StringEntity(XMLString));
			  
			  ResponseHandler<String> responseHandler = new BasicResponseHandler();
			  String responseBody = httpclient.execute(httpPost, responseHandler);
			  return(responseBody);
			} finally {
			  // When HttpClient instance is no longer needed,
			        // shut down the connection manager to ensure
			        // immediate deallocation of all system resources
			        httpclient.close();
			}
			}

		public static String getLogonTokenFromXML(String xmlString) throws Exception {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(xmlString)));
			Document doc = parser.getDocument();
			NodeList nodes = doc.getElementsByTagName("attr");

			for (int i = 0; i < nodes.getLength(); i++) {
			  Element element = (Element) nodes.item(i);
			 
			  // Is this the correct XML token
			  if (element.getAttribute("name").equals("logonToken")) {
			   return(element.getTextContent());
			  }
			}
			return("");
			}
		
		// Take a infostore query and extract the InfoObjects, their associated URL's, and the navigation URL's as well.
		// The parameter baseURL is only used to generate the "Home" link on the nav bar and the token url is used to generate
		// the opendocument links

		
		public static Document convertStringToDom(String domXMLSTring) throws Exception {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(domXMLSTring)));
			return (parser.getDocument());
			}
		
		public static String buildEncodedURLString(String basePage, String url, String urlTitle) throws Exception {
			String returnURL = "";

			 

			if (url.equals("")) {
			  returnURL = urlTitle;
			} else {
			  List< NameValuePair > qparams = new ArrayList< NameValuePair >();
			  qparams.add( new BasicNameValuePair( "queryURL", url ) );
			  returnURL = "<a href=\"" + basePage + "?" + URLEncodedUtils.format(qparams, "UTF-8") + "\">" + urlTitle + "</a>";
			}
			return(returnURL);
			}		
}
