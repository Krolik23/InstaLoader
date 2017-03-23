package com.simon.project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class App
{
    public static void main( String[] args ) throws InterruptedException {

        File pathToBinary = new File(/*Put your firefox.exe file path here, path should be in quotation marks*/);
        FirefoxBinary ffbinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile ffprofile = new FirefoxProfile();

        System.setProperty("webdriver.gecko.driver",/*Place geckodriver.exe path here, it's in project folder, remember about quotation marks*/);

        WebDriver driver = new FirefoxDriver(ffbinary, ffprofile);
        driver.get(/*Put profile address here, use quotation marks*/);




        ImageScraper scraper = new ImageScraper(driver);

        scraper.renderAllPhotos();
        scraper.imgScrap();

    }
}
