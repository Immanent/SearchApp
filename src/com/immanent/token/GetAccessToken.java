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
import org.apache.http.client.methods.HttpGet;
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
				String redirect_url = "http://" + splits[1] + "/dauth/authorize/access_token?refresh_token=" + refresh_token;
				access_token = sendGet(redirect_url);
				System.out.println(access_token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private String sendGet(String url) throws Exception {
		System.out.println(url);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		StringBuffer textView=new StringBuffer();
		// Get the response
		BufferedReader rd = new BufferedReader
		  (new InputStreamReader(response.getEntity().getContent()));
		    
		String line = "";
		while ((line = rd.readLine()) != null) {
		  textView.append(line);
		} 
		
		System.out.println(textView.toString());
 
		//JSONObject tokenObject = new JSONObject(line);
		return "";//(String) tokenObject.get("access_token");
	}
}
