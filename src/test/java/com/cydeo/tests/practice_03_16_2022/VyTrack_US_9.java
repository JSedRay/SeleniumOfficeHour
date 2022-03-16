package com.cydeo.tests.practice_03_16_2022;

import com.cydeo.utilities.Driver;
import com.cydeo.utilities.VytrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VyTrack_US_9 {

    @Test
    public void invalid_repeat_day() {

        VytrackUtils.loginAsDriver();
        VytrackUtils.waitTillLoaderMaskDisappear();

        WebElement activities = Driver.getDriver().findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[3]"));
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(activities).perform();
        Driver.getDriver().findElement(By.xpath("//span[.='Calendar Events']")).click();
        VytrackUtils.waitTillLoaderMaskDisappear();

        Driver.getDriver().findElement(By.xpath("//a[@title='Create Calendar event']")).click();
        Driver.getDriver().findElement(By.xpath("//input[starts-with(@id,'recurrence-repeat-view')]")).click();

        WebElement daysText=Driver.getDriver().findElement(By.xpath("//div[@data-name='recurrence-daily']//input[@type='text']"));
        daysText.clear();
        daysText.sendKeys("-2");

        WebElement warningText = Driver.getDriver().findElement(By.xpath("//span[.='The value have not to be less than 1.']"));
        String expected="The value have not to be less than 1.";
        String actual=warningText.getText();
        assertEquals(actual,expected);

        daysText.clear();
        daysText.sendKeys("120");
        WebElement warningText1 = Driver.getDriver().findElement(By.xpath("//span[.='The value have not to be more than 99.']"));
        String expected1="The value have not to be more than 99.";
        String actual1=warningText1.getText();
        assertEquals(actual1,expected1);
    }
}
