package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC004_ProductRemove extends BaseClass
{
	@Test(groups="master")
	public void RemoveProduct()
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
	product.clickAddToCart();
	product.RemoveProduct();
	logger.info("******Product removed********");
	Assert.assertTrue(product.isProductRemoved(),"Product removed successfully");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("***********Test case execution completed**********");

	}
}
