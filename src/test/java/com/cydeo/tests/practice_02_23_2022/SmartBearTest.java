package com.cydeo.tests.practice_02_23_2022;

import com.cydeo.utilities.WebDriverFactory;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class SmartBearTest {

    WebDriver driver;
    String appUrl = "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx";
    String userName = "Tester";
    String password = "test";

    @BeforeMethod
    public void setUp() {

        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(appUrl);

        String expectedTitle = "Web Orders Login"; // required title from documentation
        String actualTitle = driver.getTitle(); // it is coming from selenium

        Assert.assertEquals(actualTitle, expectedTitle, "Before login title did not match!");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(userName);
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    @Test
    public void loginTest() {

        String expectedTitle = "Web Orders";
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, expectedTitle, "After login title did not match!");

    }

    @Test
    public void checkBoxTest() {
//       7. Locate and click checkbox button for Paul Brown
        WebElement paulBox = driver.findElement(By.id("ctl00_MainContent_orderGrid_ctl02_OrderSelector"));
        paulBox.click();

//        8- Verify Paul Brown checkbox is selected
        Assert.assertTrue(paulBox.isSelected());
        System.out.println(paulBox.isSelected());
        paulBox.click();

//        9- Click checkbox button for All
        //     10- Verify All checkbox is selected.
        List<WebElement> boxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement eachBox : boxes) {
            eachBox.click();
            Assert.assertTrue(eachBox.isSelected());
            //System.out.println(eachBox.isSelected());
        }
    }

    @Test
    public void radioButtonTest() {
        //     7-  Locate and Click “Order”
        WebElement order = driver.findElement(By.xpath("//a[.=Order]"));
        order.click();
        //     8-  Locate and click “Visa” radio button
        WebElement visaButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
        visaButton.click();
        //     9-  Verify “Visa” radio button is selected
        Assert.assertTrue(visaButton.isSelected());
    }
    @Test
    public void dropDownTest(){
        //     7-  Locate and Click “Order”
        WebElement order = driver.findElement(By.xpath("//a[.=Order]"));
        order.click();

        //Locate Product dropDown and Select FamilyAlbum
        WebElement product = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select select = new Select(product);
        select.selectByVisibleText("FamilyAlbum");

        //9 Verify selected option is FamilyAlbum
        String expectedOption = "FamilyAlbum";
        String actualOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption, expectedOption, "FamilyAlbun option did not selected!");

    }


    @AfterMethod
    public void teardown() {
        // driver.close();
    }
}