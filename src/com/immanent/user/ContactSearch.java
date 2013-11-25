package com.immanent.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.immanent.models.ContactSearchModel;
import com.immanent.models.dao.ContactDetail;
import com.immanent.services.ServiceController;
import com.immanent.token.GetAccessToken;

/**
 * Servlet implementation class ContactSearch
 */
public class ContactSearch extends ServiceController {
	private static final long serialVersionUID = 1L;

	public ContactSearch() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch("/contact_search.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equals("search")) {
			searchContacts(request, response);
		}
	}
	
	private void searchContacts(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<ContactDetail> contactsList = new ArrayList<ContactDetail>();
		
		HttpSession session = request.getSession(false);
		String diasporaID = (String) session.getAttribute("diaspora_id");
		String accessToken = GetAccessToken.INSTANCE.getAccessToken(diasporaID);
		
		String firstName = request.getParameter("first_name").trim();
		String lastName = request.getParameter("last_name").trim();
		String diasporaHandle = request.getParameter("diaspora_handle").trim();
		String location = request.getParameter("location").trim();
		
		ContactSearchModel csm = new ContactSearchModel();
		
		try {
			//get user consumer data
			contactsList.addAll(csm.getApplicationConsumerDetails(accessToken, diasporaID));
			//get user friends list
			contactsList.addAll(csm.getFriendList(accessToken, diasporaID));
			//save contacts
			csm.saveNewContacts(contactsList);
			// search contacts
			ArrayList<ContactDetail> resultSet = csm.searchContacts(firstName, lastName,diasporaHandle, location);
			request.setAttribute("search_result", resultSet);
			request.setAttribute("diasporaID", diasporaID);
			dispatch("/search_result.jsp", request, response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
