package learnmaven.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String filePath)
            throws IOException {

    	File destinationFile = new File(filePath);

        Files.createDirectories(destinationFile.toPath().getParent());

        TakesScreenshot ts = (TakesScreenshot) driver;

        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(sourceFile, destinationFile);
    }
}