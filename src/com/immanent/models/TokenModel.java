package com.immanent.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.immanent.models.MySQLAccess;
import com.mysql.jdbc.Connection;

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

	public TokenModel(String diaspora_id) {
		setDiaspora_id(diaspora_id);
		setAuth_token(get_token("auth_token"));
		setRefresh_token(get_token("refresh_token"));
		setAccess_token(get_token("access_token"));
	}

	public void save() {
		MySQLAccess mySQLAccess = new MySQLAccess();
		Connection conn = mySQLAccess.createConnection();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO token VALUES('" + getDiaspora_id()
					+ "', '" + getAuth_token() + "', '" + getRefresh_token()
					+ "','" + getAccess_token()
					+ "') ON DUPLICATE KEY UPDATE `refresh_token`='"
					+ getRefresh_token() + "',`access_token`='"
					+ getAccess_token() + "'");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String get_token(String token_name) {
		MySQLAccess mySQLAccess = new MySQLAccess();
		Connection conn = mySQLAccess.createConnection();
		String token = "";

		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT " + token_name
					+ " FROM token WHERE diaspora_id='" + getDiaspora_id()
					+ "'");
			if (res.next()) {
				token = res.getString(token_name);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return token;

	}
}