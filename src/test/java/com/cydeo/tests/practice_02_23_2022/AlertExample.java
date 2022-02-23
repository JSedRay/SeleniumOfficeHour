package com.cydeo.tests.practice_02_23_2022;

import com.cydeo.utilities.WebDriverFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AlertExample {

    WebDriver driver;
    String url ="http://practice.cydeo.com/javascript_alerts";

    @BeforeMethod
    public void setUp(){

        driver= WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);
    }
    @Test
    public void confirmAlertTest(){

        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[.='Click for JS Confirm']"));
        jsConfirmBtn.click();

        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.accept();

        WebElement result = driver.findElement(By.id("result"));
        String expected = "You clicked: Ok";
        String actual = result.getText();
        Assert.assertEquals(actual, expected, "Result text did not appear correctly");
        //TC-5: Confirmation alert practice
//1. Open browser
// 2. Go to website: http://practice.cydeo.com/javascript_alerts
// 3. Click to “Click for JS Confirm” button
//4. Click to OK button from the alert
    }
    @Test
    public void promptAlertTest(){

        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[.='Click for JS Prompt']"));
        jsPromptBtn.click();

        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("hello");
        promptAlert.accept();

        WebElement result = driver.findElement(By.id("result"));
        String expected = "You entered: hello";
        String actual= result.getText();
        Assert.assertEquals(actual,expected,"Prompt Alert Result text did not appear correctly!");

    }
    }
   // TC-6: Information alert practice
    //1. Open browser
    //2. Go to website: http://practice.cydeo.com/javascript_alerts
    // 3. Click to “Click for JS Prompt” button
    // 4. Send “hello” text to alert
    // 5. Click to OK button from the alert
    // 6. Verify “You entered: hello” text is displayed.


