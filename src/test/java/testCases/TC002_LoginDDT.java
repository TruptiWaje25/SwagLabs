package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginDDT extends BaseClass
{
	

	@Test(groups={"Functional"}, dataProvider="Logindata", dataProviderClass=DataProviders.class)
	public void Verify_LoginDDT(String un, String pwd, String exp)
	{
		try
		{
		logger.info("*********Login testcase execution started************");
		LoginPage login = new LoginPage(getDriver());
		login.setUsername(un);
		login.setPassword(pwd);
		login.clickLogin();
		
		ProductPage product = new ProductPage(getDriver());
		boolean targetPage = product.isMyAccountExist();
		if(exp.equalsIgnoreCase("valid"))
		{
		
		if(targetPage==true)
		{
			product.clickMenu();
			product.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			
			Assert.assertTrue(false);
		}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
		
		if(targetPage==true)
		{
			product.clickMenu();
			product.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			getDriver().navigate().refresh(); 
			Assert.assertTrue(true);
		}
		}
		
		
	}
	catch(Exception e)
	{
		logger.error("Test failed...");
		logger.debug("Test Debug..");
		Assert.fail();
		
		
	}
		logger.info("*********Login testcase execution completed************");
}

}