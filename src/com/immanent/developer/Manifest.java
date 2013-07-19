package com.immanent.developer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.json.JSONException;
import org.json.JSONObject;

import com.immanent.models.MySQLAccess;

/**
 * Servlet implementation class Manifest
 */
public class Manifest extends com.immanent.services.ServiceController{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manifest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("/developer.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
		int i=0;
		while ((line = reader.readLine()) != null) {
			if (i==4){
				try {
					JSONObject jsonObj = new JSONObject(line);
					JSONObject app_details = jsonObj.getJSONObject("app_details");
					MySQLAccess dao = new MySQLAccess();
					dao.insert(app_details.getString("id"), jsonObj.getString("signed_jwt"));
					System.out.println(jsonObj.get("signed_jwt"));
				} catch (JSONException e) {
					System.out.println("Error occcured!");
					e.printStackTrace();
				}
			}
		    
		    i++;
		}
		
	}

}
