package com.immanent.token;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONObject;

import com.immanent.exceptions.ErrorMessages;
import com.immanent.models.ManifestModel;
import com.immanent.models.TokenModel;
import com.immanent.services.SendPost;
import com.immanent.services.ServiceController;

public class GetRefreshToken extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(false);
			TokenModel tokenModel = new TokenModel((String)session.getAttribute("diasporaID"));
			getToken(tokenModel, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		String diaspora_id = request.getParameter("diaspora_id");

		if (action.equals("getRefreshToken")) {
			try {

				session.setAttribute("diaspora_id", diaspora_id);

				String refresh_token = null;

				TokenModel tokenModel = new TokenModel(diaspora_id);
				refresh_token = tokenModel.getRefresh_token();

				if (refresh_token.isEmpty()) {
					getToken(tokenModel, request, response);
				} else {
					response.sendRedirect("user");
				}
			} catch (HttpHostConnectException e) {
				request.setAttribute("error", ErrorMessages.ConnectionRefuse.getErrorMessage());
				dispatch("/user.jsp", request, response);
			} catch (SocketException e) {
				request.setAttribute("error", ErrorMessages.ConnectionRefuse.getErrorMessage());
				dispatch("/user.jsp", request, response);
			} catch (UnknownHostException e) {
				request.setAttribute("error", ErrorMessages.UnknownHostException.getErrorMessage());
				dispatch("/user.jsp", request, response);
			} catch (Exception e) {
				request.setAttribute("error", ErrorMessages.InvalidDiasporaID.getErrorMessage());
				dispatch("/user.jsp", request, response);
			}

		} else {
			// get refresh token
			TokenModel token = null;
			try {
				token = new TokenModel(request.getParameter("diaspora_id"));
				token.setRefresh_token(request.getParameter("refresh_token"));
				token.save();
				GetAccessToken.INSTANCE.getAccessToken(diaspora_id); // get
																		// accesstoken

			} catch (HttpResponseException e) {
				try {
					token.setRefresh_token("");
					getToken(token, request, response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
				dispatch("/user.jsp", request, response);
			}

		}

	}

	public void getToken(TokenModel tokenModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get authentication token
		String diaspora_id = tokenModel.getDiaspora_id();
		String authToken = null;
		String[] splits = diaspora_id.split("@");
		String url = "http://" + splits[1] + "/authorize/verify";
		JSONObject responeObject = null;
		String signed_manifest = ManifestModel.INSTANCE.read();
		responeObject = SendPost.INSTANCE.postToAPI(url, "signed_manifest", signed_manifest);

		authToken = (String) responeObject.get("auth_token");
		tokenModel.setAuth_token(authToken);
		tokenModel.setDiaspora_id(diaspora_id);
		tokenModel.save();
		response.sendRedirect("http://" + splits[1] + "/dauth/authorize/authorization_token?auth_token=" + authToken + "&diaspora_handle="
				+ splits[0]);
	}
}
