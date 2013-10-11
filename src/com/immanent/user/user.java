package com.immanent.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.immanent.services.ServiceController;

/**
 * Servlet implementation class user
 */
public class user extends ServiceController {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("/home.jsp", request, response);
	}

}
