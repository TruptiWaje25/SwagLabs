package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='user-name']") WebElement txtusername;
	@FindBy(xpath="//input[@id='password']") WebElement txtpassword;
	@FindBy(xpath="//input[@id='login-button']") WebElement btnlogin;
	@FindBy(xpath="//button[@class='error-button']") WebElement btnerror;
	
	public void setUsername(String username)
	{
		//txtusername.clear();
		txtusername.sendKeys(username);
	}
	
	public void setPassword(String pwd)
	{
		//txtpassword.clear();
		txtpassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnlogin.click();
	}
	
}
