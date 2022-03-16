package com.cydeo.tests.practice_03_02_2022;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.CRM_Utilities;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CRM_CorrectModules {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){

        String browserType = ConfigurationReader.getProperty("browser");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(ConfigurationReader.getProperty("env1"));

        CRM_Utilities.crm_login(driver);

    }

    @Test
    public void leftSideActivityStreamTest(){
        WebElement actStr = driver.findElement(By.xpath("//span[contains(.,'Activity Stream')]"));
        actStr.click();
        String expectedTitle="Portal";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Activity Stream Title did not appear correctly!");

    }

    @Test
    public void leftSidebarTasksTest(){
        WebElement tasks = driver.findElement(By.xpath("//span[contains(.,'Tasks')]"));
        tasks.click();
        String expectedTitle="Site map"; // coming from requirement
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Tasks title did not appear correctly!");

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.quit();
    }
}
