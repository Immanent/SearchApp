package com.immanent.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.immanent.services.SendGet;
import com.immanent.services.ServiceController;
import com.immanent.token.GetAccessToken;

public class Friendlist extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String diasporaID = (String) session.getAttribute("diaspora_id");
		String accessToken = GetAccessToken.INSTANCE.getAccessToken(diasporaID);
		String[] splits = diasporaID.split("@");
		String url = "http://" + splits[1] + "/api/users/getUserpersonList/" + splits[0] + "@localhost:3000/" + accessToken;
		JSONObject friendList = SendGet.INSTANCE.getToApp(url);
		try {
			JSONArray friendArray = friendList.getJSONArray("user_person_list");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("friendList", friendList);
		request.setAttribute("diasporaID", diasporaID);
		dispatch("/friendList.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
