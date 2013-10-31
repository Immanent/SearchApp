package com.immanent.developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.immanent.models.DbAccess;
import com.mysql.jdbc.Connection;

public class CurrentUser {
	private String diasporaId;

	public String getDiasporaId() {
		return diasporaId;
	}

	public void setDiasporaId(String diasporaId) {
		this.diasporaId = diasporaId;
	}

	public CurrentUser() {
		DbAccess dbAccess = DbAccess.INSTANCE;
		Connection connection = dbAccess.createConnection();
		String currentUser = "";
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM current_user");
			if (res.next()) {
				currentUser = res.getString("diaspora_id");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void save(String diaspora_id) {
		DbAccess dbaccess = DbAccess.INSTANCE;
		Connection connection = dbaccess.createConnection();
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("UPDATE current_user SET diaspora_id='" + diaspora_id + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
