package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	 public Logger logger;
	 public Properties p;

	    public static void setDriver(WebDriver driverInstance) {
	        driver.set(driverInstance);
	    }
	    

	    public static WebDriver getDriver() {
	        return driver.get();
	    }
	    

	
	@BeforeClass(groups= {"Sanity","Functional"}, alwaysRun=true)
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException, MalformedURLException
	{
		System.out.println("SETUP STARTED | os=" + os + " | browser=" + br);

		FileReader file = new FileReader("./src//test//resources/config.properties");
		p=new Properties();
		p.load(file);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS");
			}
			
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome");break;
			case "firefox": cap.setBrowserName("firefox");break;
			case "edge": cap.setBrowserName("Edge");break;
			default: System.out.println("No matching browser");return;
			}
			
		
			URL gridUrl = URI.create("http://localhost:4444").toURL();
			
			WebDriver remoteDriver =
			        new RemoteWebDriver(gridUrl, cap);
			setDriver(remoteDriver);
		

			System.out.println("Driver instance: " + getDriver());

		}
	
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase()) 
		{
		case "chrome":  setDriver( new ChromeDriver(options)); break;
		case "firefox": setDriver (new FirefoxDriver());break;
		case "edge" : setDriver (new EdgeDriver()); break;
		default : System.out.print("No browser available.."); return;
		
		
		}
		}
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().get(p.getProperty("appURL"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	
	@AfterClass(groups= {"Sanity","Functional"}, alwaysRun=true)
	public void tearDown()
	{
		if (getDriver() != null) {
	        getDriver().quit();
	        driver.remove();
		}
	}
	
	public String capturescreenshot(String tname)
	{
	    WebDriver driver = getDriver();   // ✅ get actual WebDriver

	    if (driver == null) {
	        return null;
	    }
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		String targetfilePath = System.getProperty("user.dir")+"\\screenshots\\fullpage"+"_"+timeStamp+".png";
		File targetfile = new File(targetfilePath);
		sourcefile.renameTo(targetfile);
		return targetfilePath;
	}
	
	
}

