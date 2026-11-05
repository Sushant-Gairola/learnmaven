package learnmaven.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;

import learnmaven.tests.BaseTest;
import learnmaven.utilities.ScreenshotUtils;

public class TestListener implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
	    System.out.println("TEST STARTED: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println("TEST PASSED: " + result.getName());
	}

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest testClass = (BaseTest) result.getInstance();

        String testName = result.getName();

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String filePath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            ScreenshotUtils.takeScreenshot(testClass.getDriver(), filePath);

            System.out.println("Screenshot captured: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
