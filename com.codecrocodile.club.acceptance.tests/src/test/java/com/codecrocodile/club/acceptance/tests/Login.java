package com.codecrocodile.club.acceptance.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features/ui/login.feature", 
		glue="com.codecrocodile.club.acceptance.tests.steps", 
		strict=true)
public class Login { }
