package com.qa.PageLayer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.TestBase.TestBaseClass;

public class InventoryPage extends TestBaseClass{
	
		WebDriver driver;
		String product_name = super.product_name;
		String product_quantity = super.product_quantity;
		String barcode = super.barcode;
		
		
		
	public InventoryPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//div[contains(text(),'Inventory')]")
	WebElement inventory_button;
	
	@FindBy (xpath = "//a[contains(text(),'Products')]")
	WebElement product_menu;
	
	
	public void openInventory() throws InterruptedException
	{
		inventory_button.click();
		logger.info("Inventory Opened");
	}
	
	public void openProducts()
	{
		Actions act = new Actions (driver);
		act.moveToElement(product_menu).click().build().perform();
		WebElement product_button = driver.findElement(By.xpath("(//span[contains(text(),'Products')])[1]"));
		product_button.click();
		logger.info("Product Section opened");
	}
	
	public void createNewProduct() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[normalize-space()='Create']")).click();
		driver.findElement(By.xpath("//input[@class='o_field_char o_field_widget o_input o_field_translate o_required_modifier' or @placeholder='Product Name']")).sendKeys(product_name);
		driver.findElement(By.xpath("(//input[@class='o_field_char o_field_widget o_input'])[1]")).sendKeys("Satish");
		driver.findElement(By.xpath("(//input[@class='o_field_char o_field_widget o_input'])[2]")).sendKeys(barcode);
		logger.info("New Product initiated");
		
		//move to inventory tab
		driver.findElement(By.xpath("//a[@class='nav-link' and text()='Inventory']")).click();
		driver.findElement(By.xpath("//label[contains(text(),'WH/MO/01243')]")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Manufacture')]")).click();
		logger.info("New Product Details Added");
		
		//save new product
		driver.findElement(By.xpath("//button[text()='save' or @class='btn btn-primary o_form_button_save']")).click();
		logger.info("New product is created with name "+product_name);
	}
	
	public void validateNewProduct()
	{
		WebElement new_product=driver.findElement(By.xpath("(//span[@placeholder='Product Name' and @name='name'])[1]"));
		String new_product_name = new_product.getText();
		Assert.assertEquals(new_product_name, product_name);
		logger.info("New product is added as "+new_product_name+" and validated "+product_name);
	}
		
	public void updateQuantity() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[@class='o_stat_text' and text()='On Hand']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[normalize-space()='Create'])[1]")).click();
		WebElement location_list=driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		Thread.sleep(1000);
		location_list.sendKeys("WH/Stock");
		Thread.sleep(1000);
		location_list.sendKeys(Keys.ENTER);
		
		WebElement quantity= driver.findElement(By.xpath("//input[@name='inventory_quantity']"));
		Thread.sleep(1000);
		quantity.clear();
		Thread.sleep(1000);
		quantity.sendKeys(product_quantity);
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		Thread.sleep(1000);
		logger.info("Quantity of "+product_name+" is updated to "+product_quantity);
		driver.navigate().back();
		Thread.sleep(1000);
		
	}
	
	
	public void updateBill() throws InterruptedException
	{	
		String product_part = "PRD_CMP";
		driver.findElement(By.xpath("//span[contains(text(),'Bill of Materials')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary o_list_button_add']")).click();
		logger.info("New Product Product Bill initiated");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='product_qty' and @type='text']")));
		WebElement quantity = driver.findElement(By.xpath("//input[@name='product_qty' and @type='text']"));
		quantity.clear();
		Thread.sleep(1000);
		quantity.sendKeys(product_quantity);
		driver.findElement(By.xpath("//input[@class='o_field_char o_field_widget o_input']")).sendKeys("Reference");
		
		driver.findElement(By.xpath("(//a[text()='Add a line'])[1]")).click();
		Thread.sleep(1000);
		WebElement component_name= driver.findElement(By.xpath("(//input[@class='o_input ui-autocomplete-input'])[3]"));
		component_name.sendKeys(product_part);
		Thread.sleep(1000);
		component_name.sendKeys(Keys.ENTER);
		
		WebElement component_quantity= driver.findElement(By.xpath("(//input[@name='product_qty'])[2]"));
		component_quantity.clear();
		Thread.sleep(1000);
		component_quantity.sendKeys(product_quantity);
		
		driver.findElement(By.xpath("//a[text()='Miscellaneous']")).click();
		
		WebElement operation = driver.findElement(By.xpath("(//input[@type='text' and @class='o_input ui-autocomplete-input'])[3]"));
		operation.sendKeys("aspireapp: Manufacturing");
		operation.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		logger.info("New Product Bill Added");
		
		driver.findElement(By.xpath("//a[@title='Applications']")).click();

	}
}
	