package com.immanent.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public enum SendGet {
	INSTANCE;

	public JSONObject getToApp(String url) throws IOException, JSONException {
		JSONObject responseObject = null;
		String line;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			line = rd.readLine();
			//line = "{'user_person_list':[{'first_name':'Sandaruwan','last_name':'Herath','diaspora_handle':'sandaruwan@localhost:3000','location':'Maho','birthday':'1994-07-13','gender':'Male'},{'first_name':'Sandaruwan2','last_name':'Herath2','diaspora_handle':'sandaruwan2@localhost:3000','location':'Ganewaththa','birthday':'1989-08-13','gender':'Female'}]}";
			responseObject = new JSONObject(line);
			//String[] splits = responseObject.toString().split("\\");
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (JSONException e) {
			throw e;
		}

		return responseObject;

	}

}
