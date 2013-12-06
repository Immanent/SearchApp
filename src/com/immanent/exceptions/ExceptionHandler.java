package com.immanent.exceptions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.immanent.services.ServiceController;

/**
 * Servlet implementation class ExceptionHandler
 */
public class ExceptionHandler extends ServiceController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Analyze the servlet exception

		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String statusCode = (String) request.getAttribute("message");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String message = "";
		String message2 = "";
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		if (throwable == null && statusCode == null) {
			message = "Error information is missing";
		} else {
			message = "Error information";
			request.setAttribute("message", message);
			request.setAttribute("servletName", servletName);
			request.setAttribute("throwable", throwable);
			request.setAttribute("requestUri", requestUri);
		}
		dispatch("/error.jsp", request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
