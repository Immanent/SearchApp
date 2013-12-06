package com.immanent.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public enum SendGet {
	INSTANCE;

	public JSONObject getToApp(String url) throws HttpResponseException, HttpHostConnectException, IOException, JSONException, Exception {

		JSONObject responseObject = null;

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse httpResponce = httpClient.execute(httpGet);
		int statusCode = httpResponce.getStatusLine().getStatusCode();

		// get content of the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				httpResponce.getEntity().getContent()));
		StringBuffer content = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			content.append(line);
		}

		if (statusCode == 400) {
			// get error message from response
			JSONObject responseContent = new JSONObject(content.toString());
			int apiErrorCode = responseContent.getInt("error");
			if (apiErrorCode == 310 || apiErrorCode == 313 || apiErrorCode == 314 || apiErrorCode == 403){
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonList = new JSONArray();
				jsonObject.put("user_contact_list", jsonList);
				return jsonObject;
				
			}else{
				throw new HttpResponseException(statusCode, Integer.toString(apiErrorCode));
			}
		} else if (statusCode == 401) {
			throw new HttpResponseException(statusCode, Integer.toString(051));
		} else if (statusCode == 403) {
			throw new HttpResponseException(statusCode, Integer.toString(053));
		} else if (statusCode == 404) {
			throw new HttpResponseException(statusCode, Integer.toString(054));
		} else if (statusCode == 500) {
			throw new HttpResponseException(statusCode, Integer.toString(060));
		} else if (statusCode == 200) {
			responseObject = new JSONObject(content.toString());
			return responseObject;
		} else {
			throw new Exception(Integer.toString(001));
		}		
	}

}
