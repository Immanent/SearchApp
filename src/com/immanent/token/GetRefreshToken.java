package com.immanent.token;

import java.io.IOException;

import javax.servlet.ServletException;
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
		String action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		String diaspora_id = request.getParameter("diaspora_id");

		if (action.equals("getRefreshToken")) {
			try {
				String[] splits = diaspora_id.split("@");
				session.setAttribute("diaspora_id", diaspora_id);

				String url = "http://" + splits[1] + "/authorize/verify";
				String authToken = null;
				String refresh_token = null;

				TokenModel tokenModel = new TokenModel(diaspora_id);

				refresh_token = tokenModel.getRefresh_token();
				if (refresh_token.isEmpty()) {
					//get authentication token
					DbAccess dao = DbAccess.INSTANCE;
					JSONObject responeObject = null;
					String signed_manifest = dao.read();
					responeObject = SendPost.INSTANCE.postToAPI(url, "signed_manifest", signed_manifest);

					authToken = (String) responeObject.get("auth_token");
					tokenModel.setAuth_token(authToken);
					tokenModel.setDiaspora_id(diaspora_id);
					tokenModel.save();
					response.sendRedirect("http://" + splits[1] + "/dauth/authorize/authorization_token?auth_token=" + authToken
							+ "&diaspora_handle=" + splits[0]);
				}else {
					response.sendRedirect("user");
				}
			} catch (Exception e) {
			
				response.sendRedirect("ExceptionHandler");
			}

		} else {
			//get refresh token
			TokenModel token;
			try {
				token = new TokenModel(request.getParameter("diaspora_id"));
				token.setRefresh_token(request.getParameter("refresh_token"));
				System.out.println("Token -"+token.getRefresh_token());
				token.save();
			} catch (Exception e) {
				response.sendRedirect("ExceptionHandler");
			}
			//get access token
			GetAccessToken.INSTANCE.getAccessToken(diaspora_id);
		}

	}

}
