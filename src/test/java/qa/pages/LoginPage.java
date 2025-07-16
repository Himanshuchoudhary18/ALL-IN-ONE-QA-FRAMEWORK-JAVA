package qa.pages;

import org.openqa.selenium.By;
import utilsWeb.CommonFunctionsWeb;

public class LoginPage extends CommonFunctionsWeb {
    public static By crossButtonOnSurpriseMePopUp = By.xpath("//*[@id=\"radix-:r0:\"]/div[2]/button");
    public static By profileIconOnLoginPage = By.xpath("//img[@alt='Profile Image']");
    public static By loginButtonOnProfileDropDown = By.xpath("//button[@class='w-full text-left px-4 py-2 hover:bg-gray-50']");
}
