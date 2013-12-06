package com.immanent.developer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.immanent.models.ManifestModel;
import com.immanent.services.ServiceController;

/**
 * Servlet implementation class Manifest
 */
public class Manifest extends ServiceController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			if (i == 4) {
				try {
					JSONObject jsonObj = new JSONObject(line);
					JSONObject app_details = jsonObj.getJSONObject("app_details");
					ManifestModel manifestModel = ManifestModel.INSTANCE;
					manifestModel.setId(app_details.getString("id"));
					manifestModel.setManifestContent(jsonObj.getString("signed_jwt"));
					manifestModel.insert();
					//DbAccess.INSTANCE.insert(app_details.getString("id"), jsonObj.getString("signed_jwt"));
				} catch (Exception e) {
					response.sendRedirect("ExceptionHandler");
				}
			}

			i++;
		}
		dispatch("/user.jsp", request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("/developer.jsp", request, response);
	}

}
