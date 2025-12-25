package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']") WebElement clkMenu;
	@FindBy(xpath="//a[@id='logout_sidebar_link']") WebElement clklogout;
	@FindBy(xpath="//div[@class='app_logo']") WebElement logo;
	
	public void clickMenu()
	{
		clkMenu.click();
	}
   public void clickLogout()
   {
	   clklogout.click();
   }
   
   public boolean isMyAccountExist()
   {
	   try
	   {
		   return(logo.isDisplayed());
	   }
	   catch(Exception e)
	   {
		   return false;
	   }
   }
}
