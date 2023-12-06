package com.Immerstec.loginpageobject;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.Immerstec.base.BasePage;

public class LoginPage extends BasePage{

	@FindBy(xpath = "//a[contains(@class,'btn is-small is-inverse u-expanded-m')]")
	private WebElement loginPage ;
	
	@FindBy(xpath = "//*[@id=\"LoginForm_username\"]")
	private WebElement userNameField ;
	
	@FindBy(xpath = "//*[@id=\"LoginForm_password\"]")
	private WebElement passwordField ;
	
	@FindBy(xpath = "//*[@id=\"login-button\"]")
	private WebElement loginButton ;
	
	@FindBy(xpath = "//section[@class='p40y-d p20y-m']//li[1]")
	private WebElement massegeInValidDate;
	
	@FindBy(xpath = "//div[@id='LoginForm_username_em_']")
	private WebElement massegeEmpityDateUN;
	
	@FindBy(xpath = "//div[@id='LoginForm_password_em_']")
	private WebElement massegeEmpityDatePass;
	
	public void validDataLogin() throws InterruptedException  {
		action.click(loginPage);
		action.sendText(userNameField, prop.getProperty("username"));
		action.sendText(passwordField, prop.getProperty("password"));
		action.click(loginButton);
		Thread.sleep(3000);
		String currentURL = "https://www.bayt.com/en/myworkspace-j/";
		softAssert.assertEquals(action.getCurrentURL(driver),currentURL );
		softAssert.assertAll();
		//Assert.assertTrue(false);

	}
	
	public void invalidDataLogin() throws InterruptedException {
		action.click(loginPage);
		action.sendText(userNameField,("mounayousef@gmail.com"));
		action.sendText(passwordField, prop.getProperty("password"));
		action.click(loginButton);
		action.explicitWait(driver, massegeInValidDate, Duration.ofSeconds(10));
		softAssert.assertEquals(action.getText(massegeInValidDate),"You've entered an incorrect email or password, please try again.");
		softAssert.assertAll();
	}
	
	public void empitydataLogin() {
		action.click(loginPage);
		action.sendText(userNameField,("     "));
		action.sendText(passwordField, ("    "));
		action.click(loginButton);
		softAssert.assertEquals(action.getText(massegeEmpityDateUN), "This field is required");
		softAssert.assertEquals(action.getText(massegeEmpityDatePass), "This field is required");
		softAssert.assertAll();
		
	}
	
	
	
	
}
