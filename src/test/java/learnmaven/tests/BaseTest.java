package learnmaven.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
	
	protected WebDriver driver;
	Properties prop;
	
	public void loadConfig() throws IOException {

	    FileInputStream fis = new FileInputStream(
	        System.getProperty("user.dir") + "/src/test/resources/config.properties"
	    );

	    prop = new Properties();
	    prop.load(fis);

	    fis.close();
	}

    @BeforeMethod
    public void setup() throws IOException {
    	
    	loadConfig();

        System.setProperty("webdriver.chrome.driver",
        		 prop.getProperty("chromedriver"));

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        
        driver.manage().window().maximize();

        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

}
