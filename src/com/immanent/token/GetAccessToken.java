package com.immanent.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.immanent.models.TokenModel;
import com.immanent.services.SendPost;
import com.immanent.services.ServiceController;

public class GetAccessToken extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String diasporaID = (String) session.getAttribute("diaspora_id");
		String accessToken = getAccessToken(diasporaID);
		response.sendRedirect("ProfileView?access_token="+accessToken);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public String getAccessToken(String diaspora_id) {

		String refreshToken = null;
		String accessToken = null;
		TokenModel tokenModel = new TokenModel(diaspora_id);
		JSONObject tokenObject = null;

		try {
			refreshToken = tokenModel.getRefresh_token();
			accessToken = tokenModel.getAccess_token();
			if (accessToken.isEmpty()) {
				String[] splits = diaspora_id.split("@");
				String url = "http://" + splits[1] + "dauth/authorize/access_token";
				tokenObject = SendPost.INSTANCE.postToAPI(url, "access_token", refreshToken);
				try {
					accessToken = (String) tokenObject.get("access_token");
					tokenModel.setAccess_token(accessToken);
					tokenModel.save();
				} catch (Exception e) {
					System.out.println("Illegal Access Token! -" + e.getMessage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	/*
	 * private String sendPost(String url, String parameters) throws Exception {
	 * JSONObject tokenObject = null; String line; String accessToken = null;
	 * HttpClient client = new DefaultHttpClient(); HttpPost post = new
	 * HttpPost(url); List<NameValuePair> urlParameters = new
	 * ArrayList<NameValuePair>(); urlParameters.add(new
	 * BasicNameValuePair("refresh_token", parameters)); post.setEntity(new
	 * UrlEncodedFormEntity(urlParameters)); HttpResponse response =
	 * client.execute(post); BufferedReader rd = new BufferedReader(new
	 * InputStreamReader(response.getEntity().getContent())); line =
	 * rd.readLine();
	 * 
	 * try { tokenObject = new JSONObject(line); accessToken = (String)
	 * tokenObject.get("access_token"); } catch (Exception e) {
	 * System.out.println("Illegal Access Token! -" + e.getMessage()); } return
	 * accessToken; }
	 */
}
