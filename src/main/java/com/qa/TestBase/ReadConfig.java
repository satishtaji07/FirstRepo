package com.qa.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File source = new File ("./Configuration/config.properties");
		
		try {
				
			FileInputStream fis = new FileInputStream(source);
			pro = new Properties();
			pro.load(fis);
			} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String getUrl()
	{
		String url = pro.getProperty("baseurl");
		return url;
	}
	
	public String getUserName()
	{
		String username = pro.getProperty("username");
		return username;
	}
	
	public String getPassword()
	{
		String password = pro.getProperty("password");
		return password;
	}
	
	public String productName()
	{
		String new_product_name = pro.getProperty("product_name");
		return new_product_name;
	}
	
	public String productQuantity()
	{
		String product_quantity = pro.getProperty("product_quantity");
		return product_quantity;
	}
	public String barcode()
	{
		String barcode = pro.getProperty("barcode");
		return barcode;
	}
	
}
