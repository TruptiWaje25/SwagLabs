package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC007_ProductSortByAtoZ extends BaseClass
{
	@Test
	public void SortProductAtoZ()
	{
	logger.info("*****Test case execution started****");
	try
	{
	LoginPage login = new LoginPage(getDriver());
	login.setUsername(p.getProperty("username"));
	login.setPassword(p.getProperty("password"));
	login.clickLogin();
	Thread.sleep(1000);
	ProductPage product = new ProductPage(getDriver());
	product.SortByAtoZ();
	logger.info("******Product sorted from Ato Z******");
	List<String> actualNames = product.getProductNames();
	List<String> expectedNames = new ArrayList<>(actualNames);
	Collections.sort(expectedNames);
	Assert.assertEquals(actualNames,expectedNames,"Names not sorted");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("***********Test case execution completed**********");


	}
}



