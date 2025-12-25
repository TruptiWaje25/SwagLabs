package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC001_Login extends BaseClass {
	
	@Test(groups={"Sanity"})
	public void Login()
	{
		
		try
		{
		logger.info("*********Login testcase execution started************");
		LoginPage login = new LoginPage(getDriver());
		login.setUsername(p.getProperty("username"));
		login.setPassword(p.getProperty("password"));
		login.clickLogin();
		
		ProductPage product = new ProductPage(getDriver());
		Assert.assertTrue(product.isMyAccountExist());
		}
		catch(Exception e)
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("*********Login testcase execution completed************");
		
	}

}
