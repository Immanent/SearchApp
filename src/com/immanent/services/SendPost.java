package com.immanent.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public enum SendPost {
	INSTANCE;

	public JSONObject postToAPI(String url, String paraname, String parameters) throws Exception {
		JSONObject responseObject = null;
		String line;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(paraname, parameters));
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			line = rd.readLine();
			if (statusCode == 400) {
				// get error message from response
				responseObject = new JSONObject(line);
				int apiErrorCode = responseObject.getInt("error");
				throw new HttpResponseException(statusCode, Integer.toString(apiErrorCode));
			} else if (statusCode == 401) {
				throw new HttpResponseException(statusCode, Integer.toString(051));
			} else if (statusCode == 403) {
				throw new HttpResponseException(statusCode, Integer.toString(053));
			} else if (statusCode == 404) {
				throw new HttpResponseException(statusCode, Integer.toString(054));
			} else if (statusCode == 500) {
				throw new HttpResponseException(statusCode, Integer.toString(060));
			} else if (statusCode == 200) {
				responseObject = new JSONObject(line);
				return responseObject;
			} else {
				throw new Exception(Integer.toString(001));
			}
		} catch (Exception e) {
			throw e;
		}

	}

}
