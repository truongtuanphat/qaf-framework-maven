@TC001
Feature: Login

@TC001.01
Scenario: TC001.01_Valid_username_and_password
	Given I login into system
	Then I verify that successfully login
	And I logout
    
@TC001.02
Scenario: TC001.02_Invalid_username_and_password
	Given I direct to home page
	When I click on button with value 'Sign in'
	And sendKeys '${username}' into 'login.input.emailAddress'
	And sendKeys '${password}' into 'login.input.password'
	And click on 'login.button.signIn'
	And I verify error message 'Authentication failed.' is displayed in login page
	And I verify error message 'Test failed step' is displayed in login page