package com.immanent.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

import com.immanent.models.ContactSearchModel;
import com.immanent.models.TokenModel;
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
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch("/contact_search.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String diasporaID = (String) session.getAttribute("diaspora_id");
		String access_token = GetAccessToken.INSTANCE
				.getAccessToken(diasporaID);

		// testing
		diasporaID = "akilai@localhost:3000"; // TODO update
		access_token = new TokenModel().getToken(diasporaID, "access_token");

		String action = request.getParameter("action");
		String firstName;
		String lastName;
		String diasporaHandle;
		String location;

		if (action.equals("search")) {
			firstName = request.getParameter("first_name");
			lastName = request.getParameter("last_name");
			diasporaHandle = request.getParameter("diaspora_handle");
			location = request.getParameter("location");


			if (access_token.isEmpty()) {
				// TODO error msg
			}
			// send GET request Diaspora API
			HttpClient httpClient = new DefaultHttpClient();
			URI uri;
			HttpResponse res;
			try {
				String[] splits = diasporaID.split("@");
				String hostName = splits[1];
				uri = new URIBuilder()
						.setScheme("http")
						.setHost(hostName)
						.setPath(
								"/api/users/getUserpersonList/" + diasporaID
										+ "/" + access_token).build();
				HttpGet httpGet = new HttpGet(uri);
				res = httpClient.execute(httpGet);
				int statusCode = res.getStatusLine().getStatusCode();
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						res.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				ArrayList<ContactDetail> contactList = new ArrayList<ContactDetail>();

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				JSONObject responseObject = null;
				if (statusCode == 400) { // Bad request
					// TODO check result is zero or access token expires
				} else if (statusCode == 200) { // OK
					responseObject = new JSONObject(result.toString());
					JSONArray jsonArray = responseObject
							.getJSONArray("user_person_list");

					for (int i = 0; i < jsonArray.length(); i++) {

						JSONObject friendDetails = jsonArray.getJSONObject(i);
						ContactDetail person = new ContactDetail();

						if (friendDetails.get("first_name").equals(null)) {
							person.setFirstName("");
						} else {
							person.setFirstName(friendDetails
									.getString("first_name"));
						}
						if (friendDetails.get("last_name").equals(null)) {
							person.setLastName("");
						} else {
							person.setLastName(friendDetails
									.getString("last_name"));
						}
						if (friendDetails.get("location").equals(null)) {
							person.setLocation("");
						} else {
							person.setLocation(friendDetails
									.getString("location"));
						}
						if (friendDetails.get("diaspora_handle").equals(null)) {
							person.setDiasporaHandle("");
						} else {
							person.setDiasporaHandle(friendDetails
									.getString("diaspora_handle"));
						}
						if (friendDetails.get("birthday").equals(null)) {
							person.setDob("");
						} else {
							person.setDob(friendDetails.getString("birthday"));
						}
						// person.setLastName((friendDetails.get("last_name")==null)?"":(String)friendDetails.get("last_name"));
						// person.setLocation((friendDetails.get("location")==null)?"":(String)friendDetails.get("location"));
						// person.setDiasporaHandle((friendDetails.get("diaspora_handle")==null)?"":(String)friendDetails.get("diaspora_handle"));
						// person.setDob((friendDetails.get("birthday")==null)?"":(String)friendDetails.get("birthday"));
						person.setRelatedHandle(diasporaID);

						contactList.add(person);
					}
					ContactSearchModel csm = new ContactSearchModel();
					csm.saveNewContacts(contactList);
					ArrayList<ContactDetail> resultSet = new ArrayList<ContactDetail>();
					resultSet = csm.searchContacts(firstName, lastName,
							diasporaHandle, location);
					request.setAttribute("search_result", resultSet);
					dispatch("/search_result.jsp", request, response);
					// search contact

				} else {
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception en) {
				en.printStackTrace();
			}

		}
	}

}
