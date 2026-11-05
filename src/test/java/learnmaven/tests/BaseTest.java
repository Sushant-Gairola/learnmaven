package learnmaven.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
	
	protected WebDriver driver;
	
	protected Logger logger = LogManager.getLogger(BaseTest.class);
	
	Properties prop;
	
	public void loadConfig() throws IOException {

	    FileInputStream fis = new FileInputStream(
	        System.getProperty("user.dir") + "/src/test/resources/config.properties"
	    );

	    prop = new Properties();
	    prop.load(fis);

	    fis.close();
	}

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
    	
    	loadConfig();

        System.setProperty("webdriver.chrome.driver",
        		 prop.getProperty("chromedriver"));

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
        	
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--headless");
        	options.addArguments("--disable-gpu");
        	options.addArguments("--window-size=1365,768");
        	options.setCapability("pageLoadStrategy", "eager");

        	driver = new ChromeDriver(options);
            //driver = new ChromeDriver();
        	
            logger.info("Chrome browser launched successfully");
        }
        
        
        //driver.manage().window().maximize();        
        //logger.info("Browser window maximized");
        logger.info("Using default browser window size");

        driver.get(prop.getProperty("url"));
        logger.info("Application URL opened successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
    
    public WebDriver getDriver() {
        return driver;
    }

}
