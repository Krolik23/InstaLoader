package com.simon.project;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.simon.project.HelperClass.numberCeiling;

/**
 * Created by Królik on 23.10.2016.
 */
public class PageScraperTest {

    private WebDriver setDriver(){
        File pathToBinary = new File("D:/Programy/Firefox/firefox.exe");
        FirefoxBinary ffbinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile ffprofile = new FirefoxProfile();

        System.setProperty("webdriver.gecko.driver","C:/Users/Królik/IdeaProjects/InstaLoader/geckodriver.exe");

        WebDriver testDriver = new FirefoxDriver(ffbinary, ffprofile);
        testDriver.get("https://www.instagram.com/sunnystoryyy/");
        return testDriver;
    }
    @Test
    public void testImageCounter(){

        WebDriver testDriver = setDriver();
        ImageScraper testScraper = new ImageScraper(testDriver);
        double expectImageNumber = 124;

        Assert.assertEquals(expectImageNumber, testScraper.imageCounter(),0.1);
        testDriver.quit();
    }
    @Test
    public void testNumberCeiling(){
        WebDriver testDriver = setDriver();

        ImageScraper testScraper = new ImageScraper(testDriver);
        int expectedNumber = 47;

        Assert.assertEquals(expectedNumber, numberCeiling(testScraper.imageCounter()/3));
        testDriver.quit();
    }

    @Test
    public void testLinkTrimmer(){

        ImageScraper scraper = new ImageScraper();
        String testLink = "https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13774442_1213823838670513_1591670603_n.jpg?ig_cache_key=MTMxMjUxOTI0NzA3MDc0NTYwNg%3D%3D.2";
        String expectedLink = "https://scontent-waw1-1.cdninstagram.com/t51/13774442_1213823838670513_1591670603_n.jpg";
        String expectedName = "13774442_1213823838670513_1591670603_n.jpg";

        try {
            Assert.assertEquals(expectedName, scraper.trimLink(testLink).getName());
            Assert.assertEquals(expectedLink, scraper.trimLink(testLink).getFullUrl());
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }

}
