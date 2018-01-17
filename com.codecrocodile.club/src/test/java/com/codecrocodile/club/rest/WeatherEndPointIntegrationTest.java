package com.codecrocodile.club.rest;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codecrocodile.club.common.DateParam;
import com.codecrocodile.club.model.external.Weather;
import com.codecrocodile.club.testcategories.SlowIntegrationTest;

@Category(SlowIntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/root-context.xml")
public class WeatherEndPointIntegrationTest extends DBTestCase {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	@Autowired
	private WeatherEndPoint weatherEndPoint;
	
	public WeatherEndPointIntegrationTest() {
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/clubmanager" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("datasets/FlatDatabase.xml").getFile());
		
		return new FlatXmlDataSetBuilder().build(new FileInputStream(file));
	}

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
	
	@Test
	public void testGetWeather() {
		DateParam dateParam = new DateParam(dateFormat.format(new Date()));
		Weather weather = weatherEndPoint.getWeather(2, dateParam);
		
		System.out.println(weather.getMaxtempC());
		System.out.println("test done");
	}
	
}