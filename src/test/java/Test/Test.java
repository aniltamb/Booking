package Test;

import Pages.HomePage;
import Pages.SearchResultPage;
import Utility.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;


import java.time.Duration;
import java.util.List;

public class Test {

    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.booking.com/");
        List<WebElement> properties = driver.findElements(By.xpath("(//ul[contains(@class,'ia_section-container')])[1]//span[@class='ia_hotelnum']"));
        int maxProperty=0;
        WebElement maxPropertyElement=null;

        for(WebElement property: properties){
            String text=property.getText().split(" ")[0];
            while(text.contains(",")){
                String[] arr=text.split(",");
                String text2="";
                for(String sub:arr){
                    text2=text2+sub;
                }
                text=text2;
            }
            int value = Integer.parseInt(text);
            if(value>maxProperty){
                maxProperty=value;
                maxPropertyElement=property;
            }
        }
        driver.findElement(By.xpath("//span[text()='"+maxPropertyElement.getText()+"']/preceding-sibling::a")).click();

        driver.findElement(By.xpath("//div[@class='xp__dates-inner']")).click();
        List<WebElement> dates = driver.findElements(By.xpath("//td[@class='bui-calendar__date']"));
        javascriptClick(driver,dates.get(0));
        javascriptClick(driver,dates.get(3));

        adultCount(driver,5);
        addChild(driver,10);
        addRoom(driver,7);

        HomePage homePage= new HomePage(driver);
        WebElement searchButton=homePage.getSearchButton();
        BaseClass.javascriptClick(driver,searchButton);

//
//        WebElement fiveStarRatingFilter = driver.findElement(By.xpath("//span[@class='bb0670bdb4']//div[text()='5 stars']"));
//        javascriptClick(driver,fiveStarRatingFilter);

        SearchResultPage searchResultPage= new SearchResultPage(driver);
        searchResultPage.applyFiveStarRatingFilter();
        Thread.sleep(5000);
        WebElement maxRatedProperty=searchResultPage.getMaxRatedProperty();
        BaseClass.javascriptClick(driver,maxRatedProperty);
        Thread.sleep(2000);

        driver.close();
    }


    public static void javascriptClick(WebDriver driver, WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public static void adultCount(WebDriver driver,int count){
        driver.findElement(By.xpath("//div[@data-component='search/group/group-with-modal']")).click();

        WebElement adultCount = driver.findElement(By.xpath("//input[@name='group_adults']/following-sibling::span"));

        int currAdultCount= Integer.parseInt(adultCount.getText());

        if(count>currAdultCount){
            int click=count - currAdultCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='group_adults']/following-sibling::button[contains(@class,'add')]"));
                addButton.click();
                click--;
            }
        }
        if(count<currAdultCount){
            int click= currAdultCount-count;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='group_adults']/following-sibling::button[contains(@class,'subtract')]"));
                addButton.click();
                click--;
            }
        }


    }


    public static void addChild(WebDriver driver, int count){
        driver.findElement(By.xpath("//div[@data-component='search/group/group-with-modal']")).click();

        WebElement childCount = driver.findElement(By.xpath("//input[@name='group_children']/following-sibling::span"));

        int currChildCount= Integer.parseInt(childCount.getText());

        if(count>currChildCount){
            int click=count - currChildCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='group_children']/following-sibling::button[contains(@class,'add')]"));
                addButton.click();
                click--;
            }
        }
        if(count<currChildCount){
            int click= currChildCount-count;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='group_children']/following-sibling::button[contains(@class,'subtract')]"));
                addButton.click();
                click--;
            }
        }
    }

    public static void addRoom(WebDriver driver, int count){
        driver.findElement(By.xpath("//div[@data-component='search/group/group-with-modal']")).click();

        WebElement roomCount = driver.findElement(By.xpath("//input[@name='no_rooms']/following-sibling::span"));

        int currRoomCount= Integer.parseInt(roomCount.getText());

        if(count>currRoomCount){
            int click=count - currRoomCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='no_rooms']/following-sibling::button[contains(@class,'add')]"));
                addButton.click();
                click--;
            }
        }
        if(count<currRoomCount){
            int click= currRoomCount-count;
            while (click!= 0){
                WebElement addButton = driver.findElement(By.xpath("//input[@name='no_rooms']/following-sibling::button[contains(@class,'subtract')]"));
                addButton.click();
                click--;
            }
        }
    }


    public static void selectMaxRatedProperty(){

        List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='b5cd09854e d10a6220b4']"));

        double maxRating=0.0;
        WebElement maxRatingElement=null;
        for(WebElement rating:ratings){

            String strRating= rating.getText();
            double currRating = Double.parseDouble(strRating);

            if(currRating>maxRating){
                maxRating=currRating;
                maxRatingElement=rating;
            }
        }

    }


}