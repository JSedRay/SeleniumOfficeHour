package com.cydeo.tests.practice_03_02_2022;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.CRM_Utilities;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.WebDriverFactory;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CRM_SendingMessages {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){

        String browserType = ConfigurationReader.getProperty("browser");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(ConfigurationReader.getProperty("env"));

        CRM_Utilities.crm_login(driver);

    }

    @Test
    public void sendingMessagesTest(){

//        Users are on the homepage
//        Users click the MESSAGE tab
        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

//        Users write test message
        driver.switchTo().frame(driver.findElement(By.cssSelector(".bx-editor-iframe")));
        WebElement msgFrame=driver.findElement(By.tagName("body"));
        msgFrame.sendKeys("Hello!");

//        Users click the SEND button
        driver.switchTo().defaultContent();
        driver.findElement(By.id("blog-submit-button-save")).click();

//        Verify the message is displayed on the feed
        WebElement feed= driver.findElement(By.xpath("//div[starts-with(@id,'blog_post_body')]"));
        String expectedText="Hello!";
        String actualText=feed.getText();
        Assert.assertEquals(actualText,expectedText,"Message did not appear!");

    }

    @Test
    public void emptyMessageTest(){

//        Users are on the homepage
//        Users click the MESSAGE tab
        driver.findElement(By.id("feed-add-post-form-tab-message")).click();

//        Users click the SEND button
        driver.findElement(By.id("blog-submit-button-save")).click();

//        Verify “The message title is not specified” warning message is displayed on the page
        WebElement warningMsg=driver.findElement(By.xpath("//span[.='The message title is not specified']"));
        String expectedText="The message title is not specified";
        String actualText=warningMsg.getText();
        Assert.assertEquals(actualText,expectedText,"Warning Message did not appear!");

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.sleep(3);
        driver.quit();
    }

}
