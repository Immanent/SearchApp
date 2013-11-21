package com.immanent.token;

import org.json.JSONObject;

import com.immanent.models.TokenModel;
import com.immanent.services.SendPost;

public enum GetAccessToken {

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { HttpSession session =
	 * request.getSession(false); String diasporaID = (String)
	 * session.getAttribute("diaspora_id"); String accessToken =
	 * getAccessToken(diasporaID);
	 * response.sendRedirect("ProfileView?access_token="+accessToken); }
	 * 
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * }
	 */
	INSTANCE;
	public String getAccessToken(String diaspora_id) {

		System.out.println("ffff"+diaspora_id);
		String refreshToken = null;
		String accessToken = null;
		String[] splits = diaspora_id.split("@");
		String[] splits2 = splits[1].split(":");
		TokenModel tokenModel = new TokenModel(splits[0]+"@"+splits2[0]);
		JSONObject tokenObject = null;

		try {
			refreshToken = tokenModel.getRefresh_token();
			accessToken = tokenModel.getAccess_token();
			if (accessToken.isEmpty()) {
				//String url = "http://192.168.0.3:3000/dauth/authorize/access_token";
				String url = "http://"+splits[1]+"/dauth/authorize/access_token";
				tokenObject = SendPost.INSTANCE.postToAPI(url, "refresh_token", refreshToken);
				System.out.println(tokenObject.toString());
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

}
