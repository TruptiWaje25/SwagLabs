package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC005_ProductSortHigtoLowPrice extends BaseClass
{
	@Test
	public void SortProductHitoLo()
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
	product.SortByHightoLowPrice();;
	logger.info("******Product sorted by price high to low******");
	List<Double> actualPrices = product.getProductprices();
	List<Double> expectedPrices = new ArrayList<>(actualPrices);
	Collections.sort(expectedPrices, Collections.reverseOrder());
	Assert.assertEquals(actualPrices,expectedPrices,"prices not sorted");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("***********Test case execution completed**********");

	}

}
