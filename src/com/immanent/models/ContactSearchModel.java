package com.immanent.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.immanent.models.dao.ContactDetail;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContactSearchModel {

	public boolean saveNewContacts(ArrayList<ContactDetail> contactDetails) {

		boolean isSaved = false;
		PreparedStatement ps = null;

		Connection conn = DbAccess.INSTANCE.createConnection();
		String SQLQuery = "INSERT INTO contact_details (friend_handle,first_name,last_name,handle,location) VALUES (?,?,?,?,?)";

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
				isSaved = ps.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSaved;
	}

	public ArrayList<ContactDetail> searchContacts(String firstName,
			String lastName, String handle, String location) {

		Connection conn = DbAccess.INSTANCE.createConnection();
		ArrayList<ContactDetail> resultContacts = new ArrayList<ContactDetail>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String SQLQuery1 = "SELECT * FROM contact_details WHERE first_name LIKE '%"+firstName+"%' and last_name LIKE '%"+lastName+"%' and handle LIKE '%"+handle+"%' and location LIKE '%"+location+"%'"; 
		try {
			ps = (PreparedStatement) conn.prepareStatement(SQLQuery1);
			rs = ps.executeQuery();
			
			while (rs.next()){
				ContactDetail contact = new ContactDetail();

	            contact.setFirstName(rs.getString("first_name"));
	            contact.setLastName(rs.getString("last_name"));
	            contact.setLocation(rs.getString("location"));
	            contact.setDiasporaHandle(rs.getString("handle"));
	            contact.setRelatedHandle(rs.getString("friend_handle"));
	            
	            resultContacts.add(contact);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultContacts;
	}

}
