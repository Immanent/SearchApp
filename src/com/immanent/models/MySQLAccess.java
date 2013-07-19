package com.immanent.models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mysql.jdbc.Connection;

public class MySQLAccess {
	private static MySQLAccess mysqlaccess=null;
	Connection conn = null;
	
	public Connection createConnection() {
		String url = "jdbc:mysql://localhost/";
		String dbName = "manifest";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "gazab1";

		try {
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url + dbName,userName, password);
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
		return conn;

	}

	public void insert(String app_id, String manifest_content) {

		try {
			conn = createConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT into signed_content VALUES('"+ app_id + "','" + manifest_content + "')");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String read() {
		String msg =null;
		try {
			conn = createConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  signed_content");
			res.next();
			msg = res.getString("manifest_content");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}