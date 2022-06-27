package Pages;

import Utility.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    private By homePageHeading=By.xpath("//div[@data-testid='herobanner-title1']");

   private By propertyList= By.xpath("(//ul[contains(@class,'ia_section-container')])[1]//span[@class='ia_hotelnum']");

   private By guestTab = By.xpath("//div[@data-component='search/group/group-with-modal']");

   private By adultCount= By.xpath("//input[@name='group_adults']/following-sibling::span");
   private By adultAddButton= By.xpath("//input[@name='group_adults']/following-sibling::button[contains(@class,'add')]");
   private By adultMinusButton= By.xpath("//input[@name='group_adults']/following-sibling::button[contains(@class,'subtract')]");

   private By childCount= By.xpath("//input[@name='group_children']/following-sibling::span");
   private By childAddButton= By.xpath("//input[@name='group_children']/following-sibling::button[contains(@class,'add')]");
   private By childMinusButton= By.xpath("//input[@name='group_children']/following-sibling::button[contains(@class,'subtract')]");

   private By roomCount= By.xpath("//input[@name='no_rooms']/following-sibling::span");
   private By roomAddButton= By.xpath("//input[@name='no_rooms']/following-sibling::button[contains(@class,'add')]");
   private By roomMinusButton= By.xpath("//input[@name='no_rooms']/following-sibling::button[contains(@class,'subtract')]");

    private By datesTab= By.xpath("//div[@class='xp__dates-inner']");
    private By date = By.xpath("//td[@class='bui-calendar__date']");

    private By searchButton= By.xpath("//button[@class='sb-searchbox__button ']");

    public List<WebElement> getPropertyList(){
        return driver.findElements(propertyList);
    }
    public WebElement getGuestTab(){
        return driver.findElement(guestTab);
    }
    public int getAdultCount(){
        String strAdultCount = driver.findElement(adultCount).getText();
        return Integer.parseInt(strAdultCount);
    }
    public int getChildCount(){
        String strChildCount = driver.findElement(childCount).getText();
        return Integer.parseInt(strChildCount);
    }
    public int getRoomCount(){
        String strRoomCount = driver.findElement(roomCount).getText();
        return Integer.parseInt(strRoomCount);
    }
    public WebElement getHomePageHeading(){
        return driver.findElement(homePageHeading);
    }


    public void verifyHomePage(){
        boolean isDisplayed=getHomePageHeading().isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

   public WebElement getMaximumProperyLocation(){
       int maxProperty=0;
       WebElement maxPropertyElement=null;
       List<WebElement> properties = getPropertyList();
       for(WebElement property: properties){
           String text =property.getText().split(" ")[0];
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
       By maximumPropertyLocation= By.xpath("//span[text()='"+maxPropertyElement.getText()+"']/preceding-sibling::a");
     return  driver.findElement(maximumPropertyLocation);
   }

    public void addAdult(int count){
        getGuestTab().click();
        int currAdultCount= getAdultCount();
        if(count>currAdultCount){
            int click=count - currAdultCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(adultAddButton);
                addButton.click();
                click--;
            }
        }
        if(count<currAdultCount){
            int click= currAdultCount-count;
            while (click!= 0){
                WebElement minusButton = driver.findElement(adultMinusButton);
                minusButton.click();
                click--;
            }
        }
    }


    public void addChild(int count){
        int currChildCount=0;
        if(count>currChildCount){
            int click=count - currChildCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(childAddButton);
                BaseClass.javascriptClick(driver,addButton);
                click--;
            }
        }
        if(count<currChildCount){
            int click= currChildCount-count;
            while (click!= 0){
                WebElement minusButton = driver.findElement(childAddButton);
                BaseClass.javascriptClick(driver,minusButton);
                click--;
            }
        }
    }

    public void addRoom(int count){
        int currRoomCount= getRoomCount();
        if(count>currRoomCount){
            int click=count - currRoomCount;
            while (click!= 0){
                WebElement addButton = driver.findElement(roomAddButton);
                addButton.click();
                click--;
            }
        }
        if(count<currRoomCount){
            int click= currRoomCount-count;
            while (click!= 0){
                WebElement minusButton = driver.findElement(roomAddButton);
                minusButton.click();
                click--;
            }
        }
    }


    public void selectCheckinCheckoutDates(int days){
        driver.findElement(datesTab).click();
        List<WebElement> dates = driver.findElements(date);
        BaseClass.javascriptClick(driver,dates.get(0));
        BaseClass.javascriptClick(driver,dates.get(days-1));

    }

    public WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }

}
