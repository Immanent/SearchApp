package com.immanent.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public enum ManifestModel {
	INSTANCE;
	
	private Connection conn;
	private String manifestContent;
	private String id;
	
	public void insert() throws Exception {

		try {
			conn = DbAccess.INSTANCE.createConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("TRUNCATE TABLE signed_content");
			st.executeUpdate("TRUNCATE TABLE token");
			st.executeUpdate("INSERT into signed_content VALUES('" + getId() + "','" + getManifestContent() + "')");
			conn.close();
		} catch (SQLException e) {
			throw e;
		}

	}

	public String read() throws Exception {
		String msg = null;
		try {
			conn = DbAccess.INSTANCE.createConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  signed_content");
			res.next();
			msg = res.getString("manifest_content");
			conn.close();
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	public String getManifestContent() {
		return manifestContent;
	}

	public void setManifestContent(String manifestContent) {
		this.manifestContent = manifestContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
