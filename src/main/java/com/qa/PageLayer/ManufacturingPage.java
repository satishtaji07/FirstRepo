package com.qa.PageLayer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.TestBase.TestBaseClass;

public class ManufacturingPage extends TestBaseClass {

		WebDriver driver;
		String product_name = super.product_name;
		String product_quantity = super.product_quantity;
		
		
	public ManufacturingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//div[contains(text(),'Inventory')]")
	WebElement inventory_button;
	
	public void createManufacturingOrder() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[text()='Manufacturing']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Create']")).click();
		logger.info("New manufacturing Order Created");
	}
	
	public void enterOrderDetails() throws InterruptedException
	{
		WebElement order_name=driver.findElement(By.xpath("(//input[@class='o_input ui-autocomplete-input'])[1]"));
		order_name.sendKeys(product_name);
		Thread.sleep(1000);
		order_name.sendKeys(Keys.ENTER);
		logger.info("Order Details Entered Sussessfully");
	}
		
	public void saveOrder()
	{
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Confirm']")));
		driver.findElement(By.xpath("//span[text()='Confirm']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Mark as Done']")));
		driver.findElement(By.xpath("//span[text()='Mark as Done']")).click();
		
		driver.findElement(By.xpath("//span[text()='Apply']")).click();
		driver.navigate().refresh();
		logger.info("New manufacturing Order Confirmed");
			
	}
	
	public void validateOrder()
	{
		String status_expected = "Done";
		WebElement order_id_web = driver.findElement(By.xpath("//span[@name='name' and @placeholder='Manufacturing Reference']"));
		String order_id = order_id_web.getText();
				
		driver.findElement(By.xpath("//span[text()='Product Moves']")).click();
		WebElement order_id_webfinal = driver.findElement(By.xpath("(//td[@class='o_data_cell o_field_cell o_list_char'])[2]"));
		String order_id_final = order_id_webfinal.getText();
		
		WebElement status = driver.findElement(By.xpath("(//span[@class='badge badge-pill o_field_badge o_field_widget o_readonly_modifier bg-success-light' or @name='state'])[2]"));
		
		String status_actual = status.getText();
		
		Assert.assertEquals(status_actual, status_expected);
		Assert.assertEquals(order_id, order_id_final);
		logger.info("New manufacturing Order validated");
	}

}

