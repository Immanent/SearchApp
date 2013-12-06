package com.immanent.models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public enum DbAccess {
	INSTANCE;
	Connection conn = null;

	public Connection createConnection() throws Exception {
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/immanent/models/Properties/database.properties");
			prop.load(in);
			final String url = prop.getProperty("url");
			final String dbName = prop.getProperty("dbName");
			final String driver = prop.getProperty("driver");
			final String userName = prop.getProperty("userName");
			final String password = prop.getProperty("password");

			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			throw e;
		}
		return conn;

	}

/*	public void insert(String app_id, String manifest_content) throws Exception {

		try {
			conn = createConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT into signed_content VALUES('" + app_id + "','" + manifest_content + "')");
			conn.close();
		} catch (SQLException e) {
			throw e;
		}

	}*/

/*	public String read() throws Exception {
		String msg = null;
		try {
			conn = createConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  signed_content");
			res.next();
			msg = res.getString("manifest_content");
			conn.close();
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}*/
}