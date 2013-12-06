package com.immanent.tests;

import java.io.InputStream;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class TestDBSetupHelper{

	protected void cleanlyInsert(IDataSet dataSet) throws Exception {

		Properties prop = new Properties();
		InputStream in = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"com/immanent/models/Properties/database.properties");
		prop.load(in);

		String JDBC_DRIVER = prop.getProperty("driver");
		String JDBC_URL = prop.getProperty("url") + prop.getProperty("dbName");
		String USER = prop.getProperty("userName");
		String PASSWORD = prop.getProperty("password");

		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER,
				JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
}
