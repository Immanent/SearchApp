package com.immanent.models;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.immanent.models.dao.ContactDetail;
import com.immanent.services.SendGet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContactSearchModel {
	
	public ArrayList<ContactDetail> getApplicationConsumerDetails(String accessToken, String diasporaID) throws IOException, JSONException, Exception{
		
		ArrayList<ContactDetail> results = new ArrayList<ContactDetail>();
		
		String[] split = diasporaID.split("@");
		String hostName = split[1];
		String profileDetailUrl = new URIBuilder().setScheme("http").setHost(hostName).setPath("/api/users/get_user_details/" + diasporaID + "/"+ accessToken).build().toString();
		
		JSONObject responseJSON = SendGet.INSTANCE.getToApp(profileDetailUrl);
		int responceStatus = responseJSON.getInt("status_code");
		
		if (responceStatus == 400) {
			//TODO bad request
		} else if (responceStatus == 200){
			JSONObject contentJSON = new JSONObject(responseJSON.getString("content"));
			JSONObject userDetailsJSON = contentJSON.getJSONObject("user_details");
			
			ContactDetail userDetail = new ContactDetail();

			userDetail.setFirstName(userDetailsJSON.getString("first_name"));
			userDetail.setLastName(userDetailsJSON.getString("last_name"));
			userDetail.setLocation(userDetailsJSON.getString("location"));
			userDetail.setDiasporaHandle(userDetailsJSON.getString("diaspora_handle"));
			userDetail.setDob(userDetailsJSON.getString("birthday"));
			userDetail.setUrl(userDetailsJSON.getString("url"));
			userDetail.setAvatar(userDetailsJSON.getString("avatar"));

			userDetail.setRelatedHandle(diasporaID);

			results.add(userDetail);
		}		
		
		return results;
	}
	
	public ArrayList<ContactDetail> getFriendList(String accessToken, String diasporaID) throws IOException, JSONException, Exception{
		
		ArrayList<ContactDetail> results = new ArrayList<ContactDetail>();
		
		String[] split = diasporaID.split("@");
		String hostName = split[1];
		String friendListUrl = new URIBuilder().setScheme("http").setHost(hostName).setPath("/api/users/get_user_person_list/" + diasporaID+ "/" + accessToken).build().toString();
		
		JSONObject responseJSON = SendGet.INSTANCE.getToApp(friendListUrl);
		int responceStatus = responseJSON.getInt("status_code");
		
		if (responceStatus == 400) {
			// bad request
		} else if (responceStatus == 200){
			JSONObject contentJSON = new JSONObject(responseJSON.getString("content"));
			JSONArray contactsJSONArray = contentJSON.getJSONArray("user_person_list");

			for (int i = 0; i < contactsJSONArray.length(); i++) {

				JSONObject friendDetails = contactsJSONArray.getJSONObject(i);
				ContactDetail contact = new ContactDetail();

				contact.setFirstName(friendDetails.getString("first_name"));
				contact.setLastName(friendDetails.getString("last_name"));
				contact.setLocation(friendDetails.getString("location"));
				contact.setDiasporaHandle(friendDetails.getString("diaspora_handle"));
				contact.setDob(friendDetails.getString("birthday"));
				contact.setUrl(friendDetails.getString("url"));
				contact.setAvatar(friendDetails.getString("avatar"));

				contact.setRelatedHandle(diasporaID);

				results.add(contact);
			}
		}
		
		return results;
	}

	public boolean saveNewContacts(ArrayList<ContactDetail> contactDetails) throws Exception {

		boolean isSaved = false;
		PreparedStatement ps = null;

		Connection conn = DbAccess.INSTANCE.createConnection();
		String SQLQuery = "INSERT INTO contact_details (friend_handle,first_name,last_name,handle,location,url,avatar) VALUES (?,?,?,?,?,?,?)";

		if (contactDetails == null) {
			return false;
		}
		for (ContactDetail contactDetail : contactDetails) {
			try {
				ps = (PreparedStatement) conn.prepareStatement(SQLQuery);
				ps.setString(1, contactDetail.getRelatedHandle());
				ps.setString(2, contactDetail.getFirstName());
				ps.setString(3, contactDetail.getLastName());
				ps.setString(4, contactDetail.getDiasporaHandle());
				ps.setString(5, contactDetail.getLocation());
				ps.setString(6, contactDetail.getUrl());
				ps.setString(7, contactDetail.getAvatar());
				isSaved = ps.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSaved;
	}

	public ArrayList<ContactDetail> searchContacts(String firstName,
			String lastName, String handle, String location) throws Exception {

		Connection conn = DbAccess.INSTANCE.createConnection();
		ArrayList<ContactDetail> resultContacts = new ArrayList<ContactDetail>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String SQLQuery = "SELECT * FROM contact_details WHERE first_name LIKE '%"+firstName+"%' and last_name LIKE '%"+lastName+"%' and handle LIKE '%"+handle+"%' and location LIKE '%"+location+"%'"; 
		try {
			ps = (PreparedStatement) conn.prepareStatement(SQLQuery);
			rs = ps.executeQuery();
			
			while (rs.next()){
				ContactDetail contact = new ContactDetail();

	            contact.setFirstName(rs.getString("first_name"));
	            contact.setLastName(rs.getString("last_name"));
	            contact.setLocation(rs.getString("location"));
	            contact.setDiasporaHandle(rs.getString("handle"));
	            contact.setRelatedHandle(rs.getString("friend_handle"));
	            contact.setUrl(rs.getString("url"));
	            contact.setAvatar(rs.getString("avatar"));
	            
	            resultContacts.add(contact);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultContacts;
	}

}
