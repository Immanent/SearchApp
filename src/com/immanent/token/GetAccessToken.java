package com.immanent.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.immanent.models.MySQLAccess;
import com.immanent.models.TokenModel;

public class GetAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String refresh_token = null;
		String access_token = null;
		
		String diaspora_id = request.getParameter("diaspora_id");
		TokenModel tokenModel = new TokenModel(diaspora_id);
		refresh_token = tokenModel.getRefresh_token();
		access_token = tokenModel.getAccess_token();
		if (access_token.isEmpty()) {
			// Get Access Token
		}
		request.setAttribute("access_token", access_token);
		request.setAttribute("diaspora_id", "dilma@localhost:3000");
		ServletContext sc = getServletContext();  
		RequestDispatcher rd = sc.getRequestDispatcher("/ProfileView");  
		rd.forward(request, response); 
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String diaspora_id = request.getParameter("diaspora_id"); // dilma@localhost:3000
		String[] splits = diaspora_id.split("@");
		String redirect_url = "http://" + splits[1] + "/authorize/verify";
		String auth_token = null;
		TokenModel tokenModel = new TokenModel(diaspora_id);

		try {
			response.setContentType("text/html");
			auth_token = tokenModel.getAuth_token();

			if (auth_token.isEmpty()) {
				MySQLAccess dao = new MySQLAccess();
				String signed_manifest = dao.read();
				auth_token = new String(sendPost(redirect_url, signed_manifest));
				tokenModel.setAuth_token(auth_token);
				tokenModel.setDiaspora_id(diaspora_id);
				tokenModel.save();
			}

			response.sendRedirect("http://localhost:3000/dauth/authorize/authorization_token?auth_token="
					+ auth_token);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String sendPost(String url, String parameters) throws Exception {
		final String USER_AGENT = "Mozilla/5.0";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters
				.add(new BasicNameValuePair("signed_manifest", parameters));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = rd.readLine();
		JSONObject tokenObject = new JSONObject(line);
		return (String) tokenObject.get("auth_token");

	}

}
