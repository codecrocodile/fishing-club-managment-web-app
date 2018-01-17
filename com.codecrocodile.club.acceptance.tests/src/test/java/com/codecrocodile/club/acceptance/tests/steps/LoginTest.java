package com.codecrocodile.club.acceptance.tests.steps;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codecrocodile.club.acceptance.tests.util.DbUnitUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest {
	
	private WebDriver driver;
	
	private String username;
	
	private String password;

	@Before
	public void initDb() throws Exception {
//		DbUnitUtil.setUp("BasicData");
	}
	
	@After
	public void clearDb() throws Exception {
		DbUnitUtil.tearDown("StandardData");
	}

	@Given("^Im am on the Club Manager login page$")
	public void im_am_on_the_Club_Manager_login_page() throws Throwable {
		File binaryFile = new File("C://Program Files (x86)//Mozilla Firefox Old Versions//ff29.0b9//firefox.exe");
		FirefoxBinary ffBinaryFile = new FirefoxBinary(binaryFile);
		FirefoxProfile ffProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinaryFile, ffProfile);
		driver.get("http://localhost:8080/GroovyClubManager/clubapp/index.html");
	}

	@Given("^I am an admin user with username \"(.*?)\" and password \"(.*?)\"$")
	public void i_am_an_admin_user_with_username_and_password(String username, String password) throws Throwable {
		this.username = username;
		this.password = password;
	}

	@When("^I enter username and password$")
	public void i_enter_username_and_password() throws Throwable {
		WebElement usernameField = driver.findElement(By.name("j_username"));
		usernameField.sendKeys(this.username);
		WebElement passwordField = driver.findElement(By.name("j_password"));
		passwordField.sendKeys(this.password);

		passwordField.submit();
	}

	@Then("^I am logged in and taken to the home page$")
	public void i_am_logged_in_and_taken_to_the_home_page() throws Throwable {
		(new WebDriverWait(this.driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equals("club manager");
			}
		});
	}
}
