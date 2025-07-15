package qa;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.Base;
import utilsWeb.CommonFunctionsWeb;

import static utilsWeb.CommonFunctionsWeb.*;

public class Automation extends Base {
    @Test
    public void openCocaColaWebsite() {
        try {
            openURL(property.getProperty("application"), true);
            String locator = "//*[@id=\"radix-:r0:\"]/div[2]/button";
            clickByValue(locator,"Close Button on Surprise Me Pop Up");
            clickByValue("//img[@alt='Profile Image']","Profile Icon");
            clickByValue("//button[@class='w-full text-left px-4 py-2 hover:bg-gray-50']","Select Login Option");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
