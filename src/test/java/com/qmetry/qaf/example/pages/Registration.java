package com.qmetry.qaf.example.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.example.steps.StepsLibrary;

public class Registration extends StepsLibrary {
	
	@FindBy(locator = "registration.radio.title.mr")
	QAFWebElement genderMr;
	
	@FindBy(locator = "registration.radio.title.mrs")
	QAFWebElement genderMrs;
	
	@FindBy(locator = "registration.input.firstName")
	QAFWebElement firstName;
	
	@FindBy(locator = "registration.input.lastName")
	QAFWebElement lastName;
	
	@FindBy(locator = "registration.input.email")
	QAFWebElement email;
	
	@FindBy(locator = "registration.input.password")
	QAFWebElement password;
	
	@FindBy(locator = "registration.select.dobForm")
	QAFWebElement dobForm;
	
	@FindBy(locator = "registration.checkbox.newsletter")
	QAFWebElement checkboxNewsletter;
	
	@FindBy(locator = "registration.checkbox.receiveOffers")
	QAFWebElement checkboxReceiveOffers;
	
	public void selectGender(String gender) throws IOException {
		if (gender.equalsIgnoreCase("Mr."))
			clickOnElement(genderMr);
		else
			clickOnElement(genderMrs);
	}
	
	public void setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	
	public void setPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void setDateOfBirth(String dob) {
		String[] arr = dob.split("/");
		String day = arr[0];
		String month = arr[1];
		String year = arr[2];
		
		switch (month) {
			case "1":
				month = "January";
				break;
			case "2":
				month = "February";
				break;
			case "3":
				month = "March";
				break;
			case "4":
				month = "April";
				break;
			case "5":
				month = "May";
				break;
			case "6":
				month = "June";
				break;
			case "7":
				month = "July";
				break;
			case "8":
				month = "August";
				break;
			case "9":
				month = "September";
				break;
			case "10":
				month = "October";
				break;
			case "11":
				month = "November";
				break;
			case "12":
				month = "December";
				break;
		}
		new Select(this.dobForm.findElement(By.id("days"))).selectByValue(day);
		selectByValueContains((QAFWebElement)dobForm.findElement(By.id("months")), month);
		new Select(this.dobForm.findElement(By.id("years"))).selectByValue(year);
	}
	
	public void checkNewsletter(boolean isChecked) {
		if (isChecked && !checkboxNewsletter.getAttribute("class").contains("checked"))
			clickOnElement(checkboxNewsletter);
	}
}
