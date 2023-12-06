package com.Immerstec.base;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends BasePage {
	protected WebDriver driver;
	protected Logger log;
	

	@BeforeClass
	public void setUP() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		log = LogManager.getLogger(BaseTest.class.getName());
		log.info("Navigated to application URL");
	}

	@AfterClass
	public void clouser() {
		if (driver != null) {
			//driver.quit();
			log.info("Browser get closed");
		}
	}
}
