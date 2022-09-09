package com.qmetry.qaf.example.steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.text.html.HTML;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.example.pages.HomePage;
import com.qmetry.qaf.example.pages.Login;

public class TC001 extends CommonSteps{
	
	HomePage homePage = new HomePage();
	Login loginPage = new Login();
	Properties prop = new Properties();

	@QAFTestStep(description = "I login into system")
	public void loginIntoSystem() throws IOException {
		directToHomePage();
		homePage.clickOnSignInButton();
		loginPage.login();
	}
	
	@QAFTestStep(description = "I verify that successfully login")
	public void verifyThatSuccessfullyLogin() throws IOException {
		if (!loginPage.userNameIsDisplayed())
			Assert.fail("Login failed");;
	}
	
	@QAFTestStep(description = "I direct to home page")
	public void directToHomePage() throws IOException {
		InputStream input = new FileInputStream("resources/application.properties");
		prop.load(input);
		CommonStep.get(prop.getProperty("env.baseurl"));
		driver.manage().window().maximize();
	}
	
	@QAFTestStep(description = "I verify error message {error} is displayed in login page")
	public void iVerifyErrorMessageIsDisplayedInLoginPage(String error) throws IOException {
		loginPage.verifyErrorMessage(error);;
	}
	
	@QAFTestStep(description = "I logout")
	public void logout() throws IOException {
		loginPage.signOut();
	}
}
