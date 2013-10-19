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

import com.immanent.models.MySQLAccess;
import com.immanent.models.TokenModel;

public class RefreshToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		if (action.equals("getRefreshToken")) {
			String diaspora_id = request.getParameter("diaspora_id"); // dilma@localhost:3000
			session.setAttribute("diaspora_id", diaspora_id);
			String[] splits = diaspora_id.split("@");
			String redirect_url = "http://" + splits[1] + "/authorize/verify";
			String auth_token = null;
			String refresh_token = null;
			TokenModel tokenModel = new TokenModel(diaspora_id);

			try {
				refresh_token = tokenModel.getRefresh_token();
				if (refresh_token.isEmpty()) {
					MySQLAccess dao = new MySQLAccess();
					String signed_manifest = dao.read();
					auth_token = new String(sendPost(redirect_url, signed_manifest));
					tokenModel.setAuth_token(auth_token);
					tokenModel.setDiaspora_id(diaspora_id);
					tokenModel.save();
					response.sendRedirect("http://localhost:3000/dauth/authorize/authorization_token?auth_token=" + auth_token);
				}
				response.sendRedirect("user");

			} catch (Exception e) {

			}

		} else {
			TokenModel token = new TokenModel(request.getParameter("diaspora_id")); // to bereplaced
			token.setRefresh_token(request.getParameter("refresh_token"));
			token.save();
		}

	}

	private String sendPost(String url, String parameters) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("signed_manifest", parameters));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = rd.readLine();
		JSONObject tokenObject = new JSONObject(line);
		return (String) tokenObject.get("auth_token");
	}

}
