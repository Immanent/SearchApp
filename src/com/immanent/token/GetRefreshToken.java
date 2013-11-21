package com.immanent.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.immanent.models.DbAccess;
import com.immanent.models.TokenModel;
import com.immanent.services.SendPost;
import com.immanent.services.ServiceController;

public class GetRefreshToken extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Gotcha");
		String action = request.getParameter("action");
		System.out.println("Action  ="+action);
		HttpSession session = request.getSession(true);
		if (action.equals("getRefreshToken")) {
			String diaspora_id = request.getParameter("diaspora_id"); // dilma@localhost:3000
			String[] splits = diaspora_id.split("@");
			String[] splits2 = splits[1].split(":");
			session.setAttribute("diaspora_id", diaspora_id);
			
			String url = "http://" + splits[1] + "/authorize/verify";
			String authToken = null;
			String refresh_token = null;
			TokenModel tokenModel = new TokenModel(splits[0]+"@"+splits2[0]);
			try {
				refresh_token = tokenModel.getRefresh_token();
				if (refresh_token.isEmpty()) {
					DbAccess dao = DbAccess.INSTANCE;
					JSONObject responeObject = null;
					String signed_manifest = dao.read();
					responeObject = SendPost.INSTANCE.postToAPI(url, "signed_manifest", signed_manifest);
					authToken = (String) responeObject.get("auth_token");
					tokenModel.setAuth_token(authToken);
					tokenModel.setDiaspora_id(splits[0]+"@"+splits2[0]);
					tokenModel.save();
					response.sendRedirect("http://" + splits[1] + "/dauth/authorize/authorization_token?auth_token=" + authToken
							+ "&diaspora_handle=" + splits[0]);
				}
				response.sendRedirect("user");

			} catch (Exception e) {

			}

		} else {
			System.out.println("Gotcha _2");
			TokenModel token = new TokenModel(request.getParameter("diaspora_id"));
			token.setRefresh_token(request.getParameter("refresh_token"));
			System.out.println(token.getRefresh_token());
			token.save();
		}
		System.out.println("Out");
	}

	/*
	 * private String sendPost(String url, String parameters) throws Exception {
	 * HttpClient client = new DefaultHttpClient(); HttpPost post = new
	 * HttpPost(url); List<NameValuePair> urlParameters = new
	 * ArrayList<NameValuePair>(); urlParameters.add(new
	 * BasicNameValuePair("signed_manifest", parameters)); post.setEntity(new
	 * UrlEncodedFormEntity(urlParameters)); HttpResponse response =
	 * client.execute(post); BufferedReader rd = new BufferedReader(new
	 * InputStreamReader(response.getEntity().getContent())); String line =
	 * rd.readLine(); JSONObject tokenObject = new JSONObject(line); return
	 * (String) tokenObject.get("auth_token"); }
	 */

}
