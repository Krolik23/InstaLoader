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

        File pathToBinary = new File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
        FirefoxBinary ffbinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile ffprofile = new FirefoxProfile();

        System.setProperty("webdriver.gecko.driver","C:/Users/Krolik/IdeaProjects/InstaLoader — backup/geckodriver.exe");

        WebDriver driver = new FirefoxDriver(ffbinary, ffprofile);
        driver.get("https://www.instagram.com/cybulsia"); //maffashion_official




        ImageScraper scraper = new ImageScraper(driver);

        scraper.renderAllPhotos();
        scraper.imgScrap();

    }
}
