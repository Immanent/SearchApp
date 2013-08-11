package com.immanent.callbacks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.immanent.models.TokenModel;
import com.immanent.services.ServiceController;


/**
 * Servlet implementation class RefreshToken
 */
public class RefreshToken extends ServiceController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TokenModel token = new TokenModel("dilma@localhost:3000"); //to be replaced
		token.setRefresh_token(request.getParameter("refresh_token"));
		token.save();
		dispatch("/developer.jsp", request, response);
		
	}

}
