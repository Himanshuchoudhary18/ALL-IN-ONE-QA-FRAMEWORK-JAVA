package amazon.pages;

import org.openqa.selenium.By;
import utilsWeb.CommonFunctionsWeb;

public class AmazonHomePage extends CommonFunctionsWeb {
    public static By searchElement = By.id("twotabsearchtextbox");
    public static By searchButton = By.id("nav-search-submit-button");
    public static By AddToCartButton = By.id("a-autoid-4-announce");
    public static By nextAddToCartButton = By.id("a-autoid-11-announce");
}
