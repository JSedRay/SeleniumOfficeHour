package com.cydeo.tests.practice_02_16_2022;

import com.cydeo.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class TC2_MultipleButtonTest {

    public static void main(String[] args) {

        /*
        TC-2: XPATH LOCATOR practice
1. Open Chrome browser
2. Go to http://practice.cybertekschool.com/multiple_buttons
3. Click on Button 1
4. Verify text displayed is as expected:
Expected: “Clicked on button one!”
5. Locate and click Button 2
6. Verify Button 2 is displayed.
         */

        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://practice.cybertekschool.com/multiple_buttons");

        WebElement button1 =driver.findElement(By.xpath("//button[@onclick='button1()']"));
        button1.click();
        WebElement resultText = driver.findElement(By.id("result"));

        String expectedText = "Clicked on button one!";
        String actualText = resultText.getText();

        if (actualText.equals(expectedText)) {
            System.out.println("Text verification is passed");
        } else {
            System.out.println("Text verification is failed");

        }

        WebElement button2 = driver.findElement(By.xpath("//button[@onclick='button2()']"));
        button2.click();

        System.out.println("button2.isDisplayed() = " + button2.isDisplayed());
    }
}
