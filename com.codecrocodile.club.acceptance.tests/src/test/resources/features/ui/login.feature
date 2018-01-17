Feature: Club Manager Login

Scenario: Admin Login
Given Im am on the Club Manager login page
And I am an admin user with username "admin" and password "password"
When I enter username and password 
Then I am logged in and taken to the home page