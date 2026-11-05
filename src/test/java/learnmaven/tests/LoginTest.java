package learnmaven.tests;

//import java.util.concurrent.TimeUnit;


import org.testng.Assert;
import org.testng.annotations.Test;

import learnmaven.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	
	
	@Test(groups = {"regression"})
	public void verifyValidLogin() {		
		
              
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("index.php/dashboard/index"),
            "Login failed: Dashboard URL was not displayed."
        );
        
	}

	
	
	@Test(groups = {"regression"})
	public void verifyInvalidLogin() {
	    

	        LoginPage loginPage = new LoginPage(driver);

	        loginPage.enterUsername("Admin");
	        loginPage.enterPassword("wrongpassword");
	        loginPage.clickLogin();

	        String actualMessage = loginPage.getErrorMessage();

	        Assert.assertEquals(actualMessage, "Invalid credentials",
	            "Error message does not match!");

	   
	}
	
	
}

