package main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Iterator;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import rest.json.BOPropertyValues;
import entities.bo.Folders;
import entities.bo.InfoStore;
import entities.bo.LogonForm;
import entities.bo.Query;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Variables
		final String baseURL = "http://XXSAPS78.stib-mivb.be:6405/biprws";
		String userName;
		String password;
		Properties prop;
		String token = null;
		String folderName = null;
		
		//Get User and Password information
		BOPropertyValues properties = new BOPropertyValues();
		userName = properties.getPropValues().getProperty("user");
		password = properties.getPropValues().getProperty("password");
		
		//Create LogonForm instance
		LogonForm form = new LogonForm(baseURL);
		
		//Get LogonToken and convert string to JSON Object
		//----------------------------------------------
		try {
			JSONObject logonJson = new JSONObject(form.getLogon());
			
				logonJson.put("password", password);
				logonJson.put("userName", userName);
				
				
			JSONObject tokenJson = new JSONObject(form.getToken(logonJson));
			token = tokenJson.getString("logonToken");
				
			}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		//***********************************************
		
		//get the folder information
		//-----------------------------------------------
		
		Folders folder = new Folders(baseURL);
		
		try {
			
			int id;
			
			//get the topFolderID
			JSONObject topFolderJson = new JSONObject(folder.getFolderRoot(token));
			id = topFolderJson.getInt("id");
			
			//use top folder id to get all the children
			JSONObject childrenFolderJson = new JSONObject(folder.getFolderChildren(token, id));
			
			//get all entries in an array
			JSONArray childrenFolderArray = childrenFolderJson.getJSONArray("entries");
			JSONObject metaData;
			System.out.println(childrenFolderArray.toString());
			for(int i = 0; i < childrenFolderArray.length(); i++) {
				JSONObject folderJson = childrenFolderArray.getJSONObject(i);
				metaData = folderJson.getJSONObject("__metadata");
				String URI = metaData.getString("uri");
				JSONObject folderMetaData = new JSONObject(folder.getFolderMetaData(token, URI));
				System.out.println(URI);
				System.out.println(folderMetaData.toString());
			}						
			
		}
		
		catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		}


}
