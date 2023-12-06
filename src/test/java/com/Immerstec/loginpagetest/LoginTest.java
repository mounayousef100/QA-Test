package com.Immerstec.loginpagetest;

import java.time.Duration;
import org.testng.annotations.Test;
import com.Immerstec.base.BaseTest;
import com.Immerstec.loginpageobject.LoginPage;

public class LoginTest extends BaseTest {
	private LoginPage loginPage;
	
	@Test(priority = 1)
	public void validDataLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 loginPage = new  LoginPage();
		 loginPage.validDataLogin();;
	}
	
	@Test(priority = 2)
	public void invalidDataLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 loginPage = new  LoginPage();
		 loginPage.invalidDataLogin();
	}
	
	@Test(priority = 3)
	public void empityPassLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 loginPage = new  LoginPage();
		 loginPage.empitydataLogin();
	}
	
	
}
