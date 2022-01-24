package com.qa.PageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.TestBase.TestBaseClass;

public class LoginPage extends TestBaseClass{
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//input[@id='login']")
	WebElement username;
	
	@FindBy (xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement login_button;
	
//	WebElement username = driver.findElement(By.xpath("//input[@id='login']"));
//	WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
//	WebElement login_button = driver.findElement(By.xpath("//button[@type='submit']"));

	public void enterUserName(String usernameID) {
		
		username.sendKeys(usernameID);
	}
	
	public void enterPassword(String passwordID) {
		
		password.sendKeys(passwordID);
	}
	
	public void clickLogin() {
		
		login_button.click();
	}
}
