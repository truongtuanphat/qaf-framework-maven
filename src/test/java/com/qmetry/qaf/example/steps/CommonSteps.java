package com.qmetry.qaf.example.steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.example.modules.Beneficiary;
			
public class CommonSteps extends WebDriverBaseTestPage<WebDriverTestPage> {

	private final int DEFAULT_TIMEOUT = 20;
	
	private String color = "";
	private int tmpColorIndex = -1;
	private ArrayList<Integer> numList = null;
	private String[] colorList = { "#ffe6e6, #f7e6fa", "#ccf2ff, #e3d6ff", "#dbfff5, #fffadd",
			"#fff7dd, #ffdbdb", "#f5d5ff, #dbe1ff", "#d9ffe6, #fff6e2, #ffe2e2" };
	
	@QAFTestStep(description = "I click on button with value {value}")
	public void clickOnButtonWithValue(String value) {
		String xpath = String.format("//*[normalize-space(text())='%s']", value);
		QAFWebElement we = driver.findElement(By.xpath(xpath));
		clickOnElement(we);
	}
	
	@QAFTestStep(description = "I tick on checkbox {checkboxWE}")
	public void tickOnCheckbox(String checkboxWE) {
		String xpath = String.format("//*[normalize-space(text())='%s']", checkboxWE);
		QAFWebElement we = driver.findElement(By.xpath(xpath));
		clickOnElement(we);
	}
	
	public void performTextValidation(String actual, String expected) {
		Validator.verifyThat(actual, Matchers.equalTo(expected));
	}

	public boolean waitUntilElementExisted(QAFWebElement we) {
		try {
			we.waitForVisible(DEFAULT_TIMEOUT * 1000);	
			return true;
		} catch (Exception e1) {
			return false;
		}
	}
	
	public void clickOnElement(QAFWebElement p_elem) {
		int i = 0;
		do {
			try {
				setColor(p_elem);
				p_elem.click();
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Attempting to click the element: " + p_elem.getAttribute("class"));
				i += 1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while (i < DEFAULT_TIMEOUT);
	}
	
	public void clickOnElement(QAFExtendedWebElement ele) {
		try {
			ele.click();
		} catch (Exception e) {
			ele.executeScript("click()");
		}
	}
	
	public void selectByValueContains(QAFWebElement qafWESelectTag,String value) {
		List<WebElement> listOption = qafWESelectTag.findElements(By.tagName("option"));
		String valuesInList = "";
		for (WebElement option : listOption) {
			if (option.getText().toLowerCase().contains(value.toLowerCase())) {
				clickOnElement((QAFWebElement)option);
				return;
			}
			valuesInList += option.getText().trim() + ";";
		}
		Validator.verifyThat(value + " not found in list", Matchers.equalTo(valuesInList.substring(0, valuesInList.length() - 1)));
	}
	
	public void setColor(QAFWebElement we) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (tmpColorIndex == colorList.length - 1 || tmpColorIndex == -1) {
			numList = new ArrayList<>();
			for (int i = 0; i < colorList.length; i++) {
				numList.add(i);
			}
			Collections.shuffle(numList);
			tmpColorIndex = 0;
			color = colorList[numList.get(tmpColorIndex)];
		} else {
			tmpColorIndex++;
			color = colorList[numList.get(tmpColorIndex)];
		}

		js.executeScript(String.format(
				"arguments[0].setAttribute('style', 'background:linear-gradient(to right, %s) !important;color:#E74856 !important')",
				color), we);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> jsonObjectToHashMap(Object data, String objectName) throws Exception {
		JsonObject jsonObject = new Gson().toJsonTree(data).getAsJsonObject();
		HashMap<String, Object> result;
		HashMap<String, Object> resultBasicInfo;

		result = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
		JsonObject jsonObjectBasicInfo = new Gson().toJsonTree(result.get(objectName)).getAsJsonObject();
		resultBasicInfo = new ObjectMapper().readValue(jsonObjectBasicInfo.toString(), HashMap.class);
		return resultBasicInfo;
	}
	
	@SuppressWarnings("unchecked")
	public Beneficiary[] jsonObjectToBeneficiaryArray(Object data) throws Exception {
		JsonObject jsonObject = new Gson().toJsonTree(data).getAsJsonObject();
		HashMap<String, Object> result;
		ObjectMapper mapper = new ObjectMapper();

		result = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
		JsonArray jsonArrBeneficiary = new Gson().toJsonTree(result.get("BeneficiariesInfo")).getAsJsonArray();
		return mapper.readValue(jsonArrBeneficiary.toString(), Beneficiary[].class);
	}
	
	public String tryToGetHashMapValue(HashMap<String, Object> resultBasicInfo, String key) {
		try {
			return resultBasicInfo.get(key).toString();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub

	}
}
