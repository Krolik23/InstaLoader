package com.simon.project;

import com.sun.jna.platform.unix.X11;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import sun.plugin2.message.JavaScriptBaseMessage;

public class PageScroller {
    private JavascriptExecutor js;
    private Actions action;

    public PageScroller(WebDriver driver){
        js = (JavascriptExecutor)driver;
        action = new Actions(driver);
    }

    public void scrollWholePageDown(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollWholePageUp(){
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void scrollSomePixelsDown(int scrollingStartInPixels, int numberOfPixelsToScroll){

        String ScrollingStartInPixelsString = Integer.toString(scrollingStartInPixels);
        String NumberOfPixelsToScrollString = Integer.toString(numberOfPixelsToScroll);

        String ScrollArgumentInString= "window.scrollBy" + "(" + ScrollingStartInPixelsString
                + "," + NumberOfPixelsToScrollString + ")";


        js.executeScript(ScrollArgumentInString,"");
    }


    }





