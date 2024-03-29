package com.qmetry.qaf.example.steps;

import java.util.HashMap;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.example.pages.HomePage;
import com.qmetry.qaf.example.pages.Login;
import com.qmetry.qaf.example.pages.Registration;

public class TC002 extends CommonSteps {
	
	Registration registrationPage = new Registration();

	@QAFTestStep(description = "I fill user data for creating an account based on {data}")
	public void fillUserDataForCreatingAnAccountBasedOn(Object data) throws Exception {
		HashMap<String, Object> resultInfo = null;
		resultInfo = jsonObjectToHashMap(data, "PersonalInformation");
		String title = tryToGetHashMapValue(resultInfo, "title");
		String firstName = tryToGetHashMapValue(resultInfo, "firstName");
		String lastName = tryToGetHashMapValue(resultInfo, "lastName");
		String email = tryToGetHashMapValue(resultInfo, "email");
		String password = tryToGetHashMapValue(resultInfo, "password");
		String dob = tryToGetHashMapValue(resultInfo, "dob");
		boolean newsletter = Boolean.parseBoolean(tryToGetHashMapValue(resultInfo, "newsletter"));
		boolean receiveOffers = Boolean.parseBoolean(tryToGetHashMapValue(resultInfo, "receiveOffers"));

		registrationPage.selectGender(title);
		registrationPage.setFirstName(firstName);
		registrationPage.setLastName(lastName);
		registrationPage.setEmail(email);
		registrationPage.setPassword(password);
		registrationPage.setDateOfBirth(dob);
	}
  
}
