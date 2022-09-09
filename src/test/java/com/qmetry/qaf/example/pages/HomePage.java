package com.qmetry.qaf.example.pages;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.example.steps.CommonSteps;

public class HomePage extends CommonSteps {
	
	@FindBy(locator = "homepage.button.signIn")
	QAFWebElement signIn;
	
	public void clickOnSignInButton() {
		clickOnElement(signIn);
	}

}
