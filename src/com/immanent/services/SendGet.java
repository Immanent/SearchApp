package com.immanent.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public enum SendGet {
	INSTANCE;

	public JSONObject getToApp(String url) throws IOException, JSONException,
			Exception {

		JSONObject responseObject = null;

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
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
				//get error message from response
				JSONObject responseContent = new JSONObject(content.toString());
				int apiErrorCode = responseContent.getInt("error");
				
				responseObject = new JSONObject();
				responseObject.put("status_code", statusCode);
				responseObject.put("api_error_code", apiErrorCode);
				
				return responseObject;

			} else if (statusCode == 401) {
				throw new HttpResponseException(statusCode, "Unauthorized");
			} else if (statusCode == 403) {
				throw new HttpResponseException(statusCode, "Forbidden");
			} else if (statusCode == 404) {
				throw new HttpResponseException(statusCode, "Not found");
			} else if (statusCode == 500) {
				throw new HttpResponseException(statusCode, "Internal Error");
			} else if (statusCode == 200) {
				responseObject = new JSONObject();
				responseObject.put("status_code", statusCode);
				responseObject.put("content", content.toString());
				
				return responseObject;
			} else {
				throw new Exception();
			}		

		} catch (IOException e) {
			throw e;
		} catch (JSONException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}

}
