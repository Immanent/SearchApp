package com.immanent.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.immanent.services.ServiceController;

/**
 * Servlet implementation class ProfileView
 */
public class ProfileView extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		try {
			HttpSession session = request.getSession(false);
			String diasporaId = (String) session.getAttribute("diaspora_id");
			String[] splits = diasporaId.split("@");
			String url ="http://" + splits[1] + "/apiuser_profile/get_profile";
			JSONObject profile = SendPost.INSTANCE.postToAPI(url, "diaspora_id", diasporaId);
			request.setAttribute("profile", profile);
			dispatch("/profile.jsp", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	*/}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private String sendPost(String url, String parameters) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("diaspora_id", parameters));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		return rd.readLine();
	}

}
