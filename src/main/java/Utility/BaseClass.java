package Utility;

import Pages.HomePage;
import Pages.SearchResultPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseClass {

    public  WebDriver driver=null;
    public HomePage homePage;
    public SearchResultPage searchResultPage;
    public void launchBrowser(String browser){
        browser= browser.toLowerCase();
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage= new HomePage(driver);
        searchResultPage= new SearchResultPage(driver);
    }

    public void launchUrl(String url){
        driver.get("https://www.booking.com/");
    }
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void javascriptClick(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try{
        js.executeScript("arguments[0].click();", element);}
        catch(WebDriverException e){
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static void javascriptClick(WebDriver driver,WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void javascriptScrollToElement(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    public  void waitForVisibilityOf(WebElement element,int seconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void tearDown(){
        driver.quit();
    }

    public void screenshotOfPage(String name) throws IOException {
        TakesScreenshot scr = (TakesScreenshot) driver;
        File file = scr.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(".//screenshot//"+name+".png"));
    }




}
