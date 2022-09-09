package com.qmetry.qaf.example.steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;

public class TC003 extends CommonSteps {
	
	Properties prop = new Properties();
	
	@QAFTestStep(description = "I connect to the phone")
	public void iConnectToThePhone() throws IOException {
		InputStream input = new FileInputStream("resources/application.properties");
		prop.load(input);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("appPackage", "com.google.android.apps.messaging");
		cap.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		
		driver = new QAFExtendedWebDriver(new URL(""), cap);
		WebDriver aa = new ChromeDriver();
	}
  
}
