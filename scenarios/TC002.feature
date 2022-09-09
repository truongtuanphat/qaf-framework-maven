@TC002
Feature: Registration

@TC002.01
Scenario Outline: TC002.01_Registration_with_valid_fields
	Given I direct to home page
	When I click on button with value 'Sign in'
	And sendKeys '${emailAddress}' into 'login.input.createEmailAddress'
	And click on 'login.button.createAnAccount'
	And I fill user data for creating an account based on '<UserInfo>'
    Examples: {'datafile':'resources/data/TC002.json'}