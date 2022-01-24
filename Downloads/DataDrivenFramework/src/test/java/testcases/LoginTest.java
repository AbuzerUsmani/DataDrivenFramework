package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTest extends BaseTest {
	
	
	@Test
	public void BankManagerLogin() throws InterruptedException{
		
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(OR.getProperty("addCustBtn")));
	}

}
