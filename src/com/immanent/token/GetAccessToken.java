package com.immanent.token;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import com.immanent.models.TokenModel;

public class GetAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String refresh_token = null;
		String access_token = null;

		try {
			HttpSession session = request.getSession(false);
			String diaspora_id = (String) session.getAttribute("diaspora_id");

			TokenModel tokenModel = new TokenModel(diaspora_id);
			refresh_token = tokenModel.getRefresh_token();
			access_token = tokenModel.getAccess_token();
			if (access_token.isEmpty()) {
				String[] splits = diaspora_id.split("@");
				String redirect_url = "http://" + splits[1] + "/authorize/access_token";
				access_token = sendPost(redirect_url,refresh_token);
				System.out.println(access_token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private String sendPost(String url,String parameters) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("refresh_token", parameters));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = rd.readLine();
		System.out.println(line);
		JSONObject tokenObject = new JSONObject(line);
		return (String) tokenObject.get("access_token");
	}
}
