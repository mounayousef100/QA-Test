package com.Immerstec.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import com.Immerstec.actiondriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class BasePage {
	public static Action action = new Action();
	public static SoftAssert softAssert = new SoftAssert();
	public BasePage() {

		PageFactory.initElements(driver, this);
	}
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String EDGE = "edge";
	private static final String IE = "ie"; 
	private static final Map<String, Supplier<WebDriver>> BROWSER_MAP = Map.of(CHROME, ChromeDriver::new,
      FIREFOX,FirefoxDriver::new, 
      EDGE, EdgeDriver::new, 
      IE, InternetExplorerDriver::new);
	
	protected static Properties prop;
	protected static WebDriver driver;

	public static WebDriver initializeDriver() throws IOException {
		WebDriverManager.chromedriver().setup();
		prop = new Properties();
		try (FileInputStream file = new FileInputStream(getConfigFilePath())) {
			prop.load(file);
		} catch (IOException e) {
			throw new IOException("Failed to load configuration file: " + e.getMessage());
		}

		String browserName = prop.getProperty("browser");
		final int implicitWaitTimeout = 10;

		driver = BROWSER_MAP.getOrDefault(browserName.toLowerCase(), () -> {
			throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}).get();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.SECONDS);
		return driver;
	}

	private static String getConfigFilePath() {
		return System.getProperty("user.dir") + "\\Configuration\\data.properties";
	}

	public void takeScreenshot(String testName, WebDriver driver) throws IOException {
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png";
		FileUtils.copyFile(SourceFile, new File(destinationFilePath));
	}

}