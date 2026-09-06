package amazon.tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import qa.Retry;
import amazon.pages.AmazonHomePage;
import utilities.Base;
import static utilsWeb.CommonFunctionsWeb.*;

@Test(description = "Sign Up and Login Flow for Amazon Website")
public class Automation extends Base {
    @Test(priority = 1, description = "Amazon Website OpenURL | Visit Website | TC_001 : Visiting Website URL", retryAnalyzer = Retry.class, alwaysRun = true, groups = "smoke")
    public void openAmazonWebsite()
    {
        try {
            openURL(Base.getProperty().getProperty("application"), true);
            enterCharacter(AmazonHomePage.searchElement, "Shoes", "Element written in search box");
            click(AmazonHomePage.searchButton, "Search Button Clicked");
            ScrollByVisibleElement(AmazonHomePage.AddToCartButton, "Scrolled to Add To Cart Button");
            click(AmazonHomePage.nextAddToCartButton, "Clicked on next Add To Cart Button");
        }
        catch (Exception e)
        {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description());
        }
    }
}