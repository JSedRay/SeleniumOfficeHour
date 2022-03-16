package com.cydeo.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VytrackUtils {

    public static void login(String username, String password) {
        //go to website
        Driver.getDriver().get(ConfigurationReader.getProperty("env2"));
        // pass username
        // Driver.getDriver().findElement(By.cssSelector("#prependedInput"));
        Driver.getDriver().findElement(By.id("prependedInput")).sendKeys(username);
        //pass password
        Driver.getDriver().findElement(By.cssSelector("#prependedInput2")).sendKeys(password);
        //click login button
        Driver.getDriver().findElement(By.tagName("button")).click();

    }

    public static void loginAsDriver() {
        //go to website
        Driver.getDriver().get(ConfigurationReader.getProperty("env2"));
        // pass username
        // Driver.getDriver().findElement(By.cssSelector("#prependedInput"));
        Driver.getDriver().findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.getProperty("driver_username"));
        //pass password
        Driver.getDriver().findElement(By.cssSelector("#prependedInput2")).sendKeys(ConfigurationReader.getProperty("driver_password"));
        //click login button
        Driver.getDriver().findElement(By.tagName("button")).click();
    }

    public static void loginAsStoreManager() {
        //go to website
        Driver.getDriver().get(ConfigurationReader.getProperty("env2"));
        // pass username
        // Driver.getDriver().findElement(By.cssSelector("#prependedInput"));
        Driver.getDriver().findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.getProperty("store_manager_username"));
        //pass password
        Driver.getDriver().findElement(By.cssSelector("#prependedInput2")).sendKeys(ConfigurationReader.getProperty("store_manager_password"));
        //click login button
        Driver.getDriver().findElement(By.tagName("button")).click();
    }

    public static void loginAsSalesManager() {
        //go to website
        Driver.getDriver().get(ConfigurationReader.getProperty("env2"));
        // pass username
        // Driver.getDriver().findElement(By.cssSelector("#prependedInput"));
        Driver.getDriver().findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.getProperty("sales_manager_username"));
        //pass password
        Driver.getDriver().findElement(By.cssSelector("#prependedInput2")).sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        //click login button
        Driver.getDriver().findElement(By.tagName("button")).click();
    }

    public static void waitTillLoaderMaskDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
            WebElement loaderMask = Driver.getDriver().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param driver
     * @param tab
     * @param module
     */
    public static void navigateToModule(String tab, String module) {
        BrowserUtils.sleep(2);//span[normalize-space()='Fleet'] [@class='title title-level-1']
        String tabLocator = "//span[normalize-space()='"+tab+"'][@class='title title-level-1']";
        String moduleLocator = "//span[normalize-space()='"+module+"'][@class='title title-level-2']";

        WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
        new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).perform();

        BrowserUtils.waitForVisibility(By.xpath(moduleLocator), 10).click();

    }
}
