package udc.tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import qa.Retry;
import udc.pages.Dashboard;
import udc.pages.LoginPage;
import udc.pages.Profile;
import utilities.Base;
import static utilsWeb.CommonFunctionsWeb.*;
import static utilsWeb.CommonFunctionsWeb.enterCharacter;

@Test(description = "Sign Up and Login Flow for UDC Website")
public class Automation extends Base {
    @Test(priority = 1,description = "UDC Website OpenURL | Visit Website | TC_001 : Visiting Website URL", retryAnalyzer = Retry.class, alwaysRun = true, groups = "smoke")
    public void openUDCWebsite() {
        try {
              openURL(Base.getProperty().getProperty("application"), true);
              click(LoginPage.visitWebsiteButton, "Clicked On Visit Website Button");
        }
        catch (Exception e)
        {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description());
        }
    }

    @Test(priority = 2, description = "Validating Login Flow | Login & Sign Up Flow | TC_002 : Validating Email and Password", alwaysRun = true, enabled = true, groups = "sanity")
    public void ValidateLoginPage() {
        try {
            enterCharacter(LoginPage.emailButton, "brampton@ultimatedrivers.ca", "Email Submitted");
            enterCharacter(LoginPage.passwordButton, "Mehta@12345", "Password Submitted");
            click(LoginPage.LoginButton, "Login Button Clicked");
        }
        catch (Exception e)
        {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description() + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Validating Dashboard Profile Photo and Signature | Profile flow | TC_003 : Validating Dashboard Profile and Signature", alwaysRun = true, enabled = true, groups = "sanity")
    public void ValidateProfile()
    {
        try
        {
            click(Profile.ClickRemoveProfilePhoto, "Removed Profile Photo");
            uploadViaNativeDialog(Profile.ClickEditProfilePhoto,"FAQs","Profile Picture");
            System.out.println("Photo uploaded");
            safeClick(Profile.SubmitButton1, "Submit clicked with Safe Button");

            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            System.out.println("Error : "+ e.getMessage());
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description() + e.getMessage());
        }
    }

    @Test(priority = 4, description = "Verifying Dashboard Functionality | Dashboard flow | TC_004 : Verifying View Tasks link in Pending Tasks | TC_005 : Verifying Location Filter", alwaysRun = true, enabled = true, groups = "sanity")
    public void ValidateDashboard()
    {
        try
        {
            click(Dashboard.AllLocationFilter, "Clicked to change the location");
            enterCharacter(Dashboard.SearchLocation, "BRAMPTON", "Search results in Location Search Column");
            click(Dashboard.checkboxSelection, "Location checkbox selected");
            click(Dashboard.selectLocation, "Select Locations Button Clicked");
        }
        catch (Exception e)
        {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description() + e.getMessage());
        }
    }
}
