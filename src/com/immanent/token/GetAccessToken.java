package com.immanent.token;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.immanent.models.TokenModel;

public class GetAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String refresh_token = null;
		String access_token = null;
		
		HttpSession session = request.getSession(false);
		String diaspora_id = (String)session.getAttribute("diaspora_id");
	    System.out.println("It woe  ="+diaspora_id);
		TokenModel tokenModel = new TokenModel(diaspora_id);
		refresh_token = tokenModel.getRefresh_token();
		access_token = tokenModel.getAccess_token();
		if (access_token.isEmpty()) {
			// Get Access Token
			System.out.println("No access tokens!");
		}
		/*request.setAttribute("access_token", access_token);
		request.setAttribute("diaspora_id", "dilma@localhost:3000");
		ServletContext sc = getServletContext();  
		RequestDispatcher rd = sc.getRequestDispatcher("/ProfileView");  
		rd.forward(request, response); */
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {}


}
