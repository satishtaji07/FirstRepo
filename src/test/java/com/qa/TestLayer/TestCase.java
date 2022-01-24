package com.qa.TestLayer;

import org.testng.annotations.Test;

import com.qa.PageLayer.InventoryPage;
import com.qa.PageLayer.LoginPage;
import com.qa.PageLayer.ManufacturingPage;
import com.qa.TestBase.TestBaseClass;

public class TestCase extends TestBaseClass{
	
	
	@Test
	public void testCase() throws InterruptedException
	{
		LoginPage lp= new LoginPage (driver);
		lp.enterUserName(username);
		lp.enterPassword(password);
		lp.clickLogin();
		
		InventoryPage ip = new InventoryPage(driver);
		ip.openInventory();
		ip.openProducts();
		ip.createNewProduct();
		ip.validateNewProduct();
		ip.updateQuantity();
		ip.updateBill();
		
		ManufacturingPage mp = new ManufacturingPage(driver);
		mp.createManufacturingOrder();
		mp.enterOrderDetails();
		mp.saveOrder();
		mp.validateOrder();
		
	
	}
	
		
}

