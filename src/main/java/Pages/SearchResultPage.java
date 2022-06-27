package Pages;

import Utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {

    WebDriver driver;

    public SearchResultPage(WebDriver driver){
        this.driver=driver;
    }


    private  By searchResultBreadcrum= By.xpath("//div[@data-testid='breadcrumbs']//span[text()='Search results']");
    private By fiveStarRatingFilter=By.xpath("//span[@class='bb0670bdb4']//div[text()='5 stars']");
    private By ratings=By.xpath("//div[contains(@class,'d10a6220b4')]");

    public WebElement getSearchResultBreadcrum(){
        return driver.findElement(searchResultBreadcrum);
    }

    public void applyFiveStarRatingFilter(){
        BaseClass.javascriptClick(driver,driver.findElement(fiveStarRatingFilter));
    }

    public List<WebElement> getPropertyRatings(){
        return driver.findElements(ratings);
    }

    public WebElement getMaxRatedProperty(){
        List<WebElement> propertyRatings = getPropertyRatings();

        double maxRating=0.0;
        WebElement maxRatingElement=null;

        for(WebElement rating:propertyRatings){
            double currRating = Double.parseDouble(rating.getText());
            if(currRating>maxRating){
                maxRating=currRating;
                maxRatingElement=rating;
            }
        }
        return maxRatingElement;
    }


}
