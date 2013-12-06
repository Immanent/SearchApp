package com.immanent.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.immanent.models.DbAccess;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TokenModel {
	private String diaspora_id;
	private String auth_token;
	private String refresh_token;
	private String access_token;

	public String getDiaspora_id() {
		return diaspora_id;
	}

	public void setDiaspora_id(String diaspora_id) {
		this.diaspora_id = diaspora_id;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public TokenModel() {
	}

	public TokenModel(String diaspora_id) throws Exception {
		setDiaspora_id(diaspora_id);
		setAuth_token(get_token("auth_token"));
		setRefresh_token(get_token("refresh_token"));
		setAccess_token(get_token("access_token"));
	}

	public void save() throws Exception {
		Connection conn = DbAccess.INSTANCE.createConnection();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO token VALUES('" + getDiaspora_id() + "', '" + getAuth_token() + "', '" + getRefresh_token() + "','"
					+ getAccess_token() + "') ON DUPLICATE KEY UPDATE 'diaspora_id='"+getDiaspora_id()+"'auth_token='"+getAuth_token()+"`refresh_token`='" + getRefresh_token() + "',`access_token`='"
					+ getAccess_token() + "'");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String get_token(String token_name) throws Exception {
		Connection conn = DbAccess.INSTANCE.createConnection();
		String token = "";

		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT " + token_name + " FROM token WHERE diaspora_id='" + getDiaspora_id() + "'");
			if (res.next()) {
				token = res.getString(token_name);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return token;

	}
	
	public String getToken(String diasporaID, String tokenType){

		String token = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT " + tokenType + " FROM token WHERE diaspora_id=?";

		if (diasporaID.isEmpty())
			return "";

		try {
			Connection conn = DbAccess.INSTANCE.createConnection();
			ps = (PreparedStatement) conn.prepareStatement(SQLQuery);
			ps.setString(1, diasporaID);
			rs = ps.executeQuery();
			if (rs.next()) {
				token = rs.getString(tokenType);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}
	
}
