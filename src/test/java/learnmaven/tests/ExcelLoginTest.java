package learnmaven.tests;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import learnmaven.pages.LoginPage;
import learnmaven.utilities.ExcelUtils;
import learnmaven.listeners.TestListener;


@Listeners(TestListener.class)
public class ExcelLoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/LoginData.xlsx";

        return ExcelUtils.getExcelData(filePath, "Sheet1");
    }

    @Test(dataProvider = "loginData", groups = {"regression"})
    public void verifyLogin(String username, String password, String expectedResult) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedResult.equalsIgnoreCase("Valid")) {

            Assert.assertTrue(
                driver.getCurrentUrl().contains("index.php/dashboard/index"),
                "Login failed for valid credentials!"
            );

        } else {

            Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Error message does not match!"
            );
        }
    }
    
  /* @Test
      public void screenshotTest() {

        Assert.fail("Intentional failure to test screenshot capture");

    }*/
}