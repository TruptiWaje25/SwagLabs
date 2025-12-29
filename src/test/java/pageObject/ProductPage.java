package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']") WebElement clkMenu;
	@FindBy(xpath="//a[@id='logout_sidebar_link']") WebElement clklogout;
	@FindBy(xpath="//div[@class='app_logo']") WebElement logo;
	@FindBy(xpath="//button[contains(text(),'Add to cart')]") WebElement clkaddcart;
	@FindBy(xpath="//button[contains(text(),'Remove')]") WebElement clkremove;
	@FindBy(xpath="//select[@class=\"product_sort_container\"]") WebElement clksort;
	@FindBy(xpath="//div[@class=\"inventory_item_price\"]") List<WebElement> productprices; 
	@FindBy(xpath="//div[@class='inventory_item_name']")List<WebElement> productnames;
	
	
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
   
   public void clickAddToCart()
   {
	   clkaddcart.click();   }


   public boolean isProductAddedtoCart()
   {
	return clkremove.isDisplayed();
   }
   
   public void RemoveProduct()
   {
	   clkremove.click();
   }
   
   public boolean isProductRemoved()
   {
	return clkaddcart.isDisplayed();
   }
  public void SortByHightoLowPrice()
  {
	  Select drpSortBy = new Select(clksort);
	  drpSortBy.selectByValue("hilo");
  }
  public void SortByLowToHighPrice()
  {
	  Select drpSortBy = new Select(clksort);
	  drpSortBy.selectByValue("lohi");
  }
  public void SortByAtoZ()
  {
	  Select drpSortBy = new Select(clksort);
	  drpSortBy.selectByValue("az");
  }
  public void SortByZtoA()
  {
	  Select drpSortBy = new Select(clksort);
	  drpSortBy.selectByValue("za");
  }
  
  public List<Double> getProductprices()
  {
	  List<Double> prices = new ArrayList<>(); 
	  for(WebElement price:productprices)
	  {
		  prices.add(Double.parseDouble(price.getText().replace("$", "")));
		 System.out.print(prices);

	  }
	
	return prices;
	  
  }
  
  public List<String> getProductNames()
  {
	  
	return productnames.stream().map(WebElement::getText).toList();
	  
  }
}