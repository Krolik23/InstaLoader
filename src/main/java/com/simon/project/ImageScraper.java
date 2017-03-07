package com.simon.project;


import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;

import static com.simon.project.HelperClass.numberCeiling;


public class ImageScraper {
    private WebDriver driver;
    private final int DefaultNumberOfImgs = 12;


    public ImageScraper(){}
    public ImageScraper(WebDriver driver){
        this.driver = driver;
    }


    public void imgScrap() {
        try {
            driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
            Document doc = Jsoup.parse(driver.getPageSource());
            int counter = 1;
            driver.quit();


            Elements body = doc.select("article._42elc div div._nljxa");
            for (Element el : body.select("div._myci9")) {
                for (Element image : el.select("a._8mlbc div._22yr2 div._jjzlb img")) {
                    String image_url = image.attr("src");
                    System.out.println("Pobieram zdjęcie nr " + counter);
                    counter++;
                    imageDownloader(trimLink(image_url).getFullUrl(),trimLink(image_url).getName());
                }
            }
        }
        catch(Exception exception){
            System.out.println("Error occurred");
        }
    }

    public double imageCounter(){
        Document InstaProfileHeader = Jsoup.parse(driver.getPageSource());
        Elements HeaderElements = InstaProfileHeader.select("header._5axto");
        String NumberOfImagesInString = HeaderElements.select("div._de9bg span._s53mj span").first().text();
        double NumberOfImages = Double.parseDouble(NumberOfImagesInString.replaceAll("[ |\\t]",""));
        return NumberOfImages;
    }



    public void renderAllPhotos() throws InterruptedException{
        PageScroller scroller = new PageScroller(driver);
        int defaultStartScrollingPixels = 1290;
        int pixelsToScroll = 980;
        Integer numberOf_Myci9 = 12;
        Integer previousNumberOf_Myci9 = 0;
        Integer _myci9ToLoad = numberCeiling(imageCounter()/3);

        WebElement LoadMoreButton = driver.findElement(By.xpath("/html/body/span/section/main/article/div/a"));  ///html/body/span/section/main/article/div/div[3]/a
        LoadMoreButton.click();
        Thread.sleep(400);
        scroller.scrollSomePixelsDown(0,pixelsToScroll);
        while(numberOf_Myci9 < _myci9ToLoad){

            Thread.sleep(460);//(460)
            /*Try this instead of use Thread.sleep() everywhere: WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));*/
            scroller.scrollSomePixelsDown(0,defaultStartScrollingPixels);
            defaultStartScrollingPixels+=1290;


            Document doc = Jsoup.parse(driver.getPageSource());
            previousNumberOf_Myci9 = numberOf_Myci9;
            numberOf_Myci9 = doc.select("article._42elc div div._nljxa div._myci9").size();
            if(numberOf_Myci9.equals(previousNumberOf_Myci9) && numberOf_Myci9 > 12){
                break;
            }
        }
    }


    public InstaPhoto trimLink(String toTrimURL) throws Exception{

        final String urlPart = "https://instagram.fwaw5-1.fna.fbcdn.net/t51/";
        Pattern trimPattern = Pattern.compile("([a-z_0-9]*.jpg)");
        Matcher matcher = trimPattern.matcher(toTrimURL);

        if(matcher.find()){
            String trimmedURL = matcher.group(1);
            InstaPhoto photo = new InstaPhoto(trimmedURL,urlPart+trimmedURL);
            return photo;
        }
        else throw new Exception("Unable to find proper matcher");
    }

    public void imageDownloader(String linkToDownload,String name){
        String path = "C:/Users/Krolik/Desktop/InstaLoader/";
        try{
            URL url = new URL(linkToDownload);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(path+name);

            byte[] b = new byte[2048];
            int length;
            while((length = is.read(b)) != -1){
                os.write(b,0,length);
            }

            is.close();
            os.close();

        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }




}
