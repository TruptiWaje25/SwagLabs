package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC003_ProductAddtocart extends BaseClass
{
	@Test
	public void AddToCart()
	{
		logger.info("**************Test case execution started**********************");
		try
		{
		LoginPage login = new LoginPage(getDriver());
		login.setUsername(p.getProperty("username"));
		login.setPassword(p.getProperty("password"));
		login.clickLogin();
		ProductPage product = new ProductPage(getDriver());
		product.clickAddToCart();
		logger.info("******product added to cart*******");
		Assert.assertTrue(product.isProductAddedtoCart(),"Product added to cart");
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**************Test case execution completed*************************");
	}
	
}