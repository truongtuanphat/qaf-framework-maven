package com.qmetry.qaf.example.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.aspectj.org.eclipse.jdt.core.Flags;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.locators.RelativeLocator;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.example.steps.StepsLibrary;

public class Login extends StepsLibrary {
	
	@FindBy(locator = "login.input.emailAddress")
	QAFWebElement emailAddress;
	
	@FindBy(locator = "login.input.password")
	QAFWebElement password;
	
	@FindBy(locator = "login.button.signIn")
	QAFWebElement signIn;
	
	@FindBy(locator = "login.button.userName")
	QAFWebElement userName;
	
	@FindBy(locator = "login.button.signOut")
	QAFWebElement signOut;
	
	@FindBy(locator = "login.text.errorMessage")
	QAFWebElement errorMessage;
	
	QAFWebElement qafWE;
	
	public void login() throws IOException {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("resources/application.properties");
		prop.load(input);
		emailAddress.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		clickOnElement(signIn);
	}
	
	public boolean userNameIsDisplayed() {
		return waitUntilElementExisted(userName)?true:false;
	}
	
	public void verifyErrorMessage(String error) {
		List<QAFWebElement> listError = driver.findElements("login.text.errorMessage");
		boolean flag = false;
		String actualListError = "";
		for (QAFWebElement actualError : listError) {
			actualListError += actualError.getText() + ";";
			if (actualError.getText().equalsIgnoreCase(error)) {
				flag = true;
				break;
			}	
		}
		if (!flag)
			Validator.verifyThat(actualListError.substring(0,actualListError.length()-1), Matchers.equalTo(error));
	}
	
	public void signOut() {
		clickOnElement(signOut);
	}
}
