package learnmaven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	//private WebDriver driver;
	private WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	       // this.driver = driver;
	        this.wait = new WebDriverWait(driver, 20);
	    }
	    
	 // Enter username
	    public void enterUsername(String username) {
	       

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.name("username")
	        )).sendKeys(username);
	    }

	    // Enter password
	    public void enterPassword(String password) {
	        

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.name("password")
	        )).sendKeys(password);
	    }
	    
	 // Click Login button
	    public void clickLogin() {
	        

	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	        )).click();
	    }
	    
	    
	    public String getErrorMessage() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//p[contains(@class,'oxd-alert-content-text')]")
	        )).getText();
	    }

}
