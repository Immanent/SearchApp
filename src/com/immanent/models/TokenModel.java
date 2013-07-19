package com.immanent.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.immanent.models.MySQLAccess;
import com.mysql.jdbc.Connection;

public class TokenModel {
	private String diaspora_id;
	private String auth_token;

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
	
	public void save() {
		MySQLAccess mySQLAccess = new MySQLAccess();
		Connection conn =mySQLAccess.createConnection();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT into token VALUES('"+ getDiaspora_id() + "','" + getAuth_token() + "')");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	public String get(String diaspora_id) {
		MySQLAccess mySQLAccess = new MySQLAccess();
		Connection conn =mySQLAccess.createConnection();
		String token ="";
		
		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT duth_token FROM token WHERE diaspora_id='"+diaspora_id+"'");
			if(res.next()){				
				token = res.getString("duth_token");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return token;
		
	}
}
