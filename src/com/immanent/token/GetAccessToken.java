package com.immanent.token;

import org.json.JSONObject;

import com.immanent.models.TokenModel;
import com.immanent.services.SendPost;

public enum GetAccessToken {

	INSTANCE;
	public String getAccessToken(String diaspora_id) {

		String refreshToken = null;
		String accessToken = null;
		String[] splits = diaspora_id.split("@");
		try {
			TokenModel tokenModel = new TokenModel(diaspora_id);
			JSONObject tokenObject = null;

			refreshToken = tokenModel.getRefresh_token();
			accessToken = tokenModel.getAccess_token();

			String url = "http://" + splits[1]+ "/dauth/authorize/access_token";
			tokenObject = SendPost.INSTANCE.postToAPI(url, "refresh_token",refreshToken);
			System.out.println(tokenObject.toString());
			try {
				accessToken = (String) tokenObject.get("access_token");
				tokenModel.setAccess_token(accessToken);
				tokenModel.save();
			} catch (Exception e) {
				System.out.println("Illegal Access Token! -" + e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

}
