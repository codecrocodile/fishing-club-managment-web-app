package com.codecrocodile.club.acceptance.tests.util;

import java.io.File;
import java.io.FileInputStream;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class DbUnitUtil {

	public static void setUp(String dataSetPath) throws Exception {
		JdbcDatabaseTester dbt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/clubmanager", "root", "");
		IDatabaseConnection connection = dbt.getConnection();
		
		ClassLoader classLoader = DbUnitUtil.class.getClassLoader();
		File file = new File(classLoader.getResource("datasets/FlatDatabase.xml").getFile());
		IDataSet dataSet =  new FlatXmlDataSetBuilder().build(new FileInputStream(file));

		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} finally {
			connection.close();
		}
	}

	public static void tearDown(String dataSetPath) {

	}

}
