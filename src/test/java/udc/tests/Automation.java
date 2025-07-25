package udc.tests;

import com.aventstack.extentreports.Status;
import com.redis.Log;
import org.testng.annotations.Test;
import qa.Retry;
import udc.pages.LoginPage;
import utilities.Base;
import utilsDatabase.ConnectionManagerMySQL;

import static utilsWeb.CommonFunctionsWeb.*;

@Test(description = "Sign Up and Login Flow for UDC Website")
public class Automation extends Base {
//    @DataProvider(name = "LoginCredentials")
//    public Object[][] createData1() {
//        return new Object[][]{
//                {"7206391749", "1212"}
//        };
//    }

    @Test(description = "UDC Website | Login & Sign Up Flow | LoginSignUp_HLS_001_TC_001 : Verifying and Validating Invalid Login Credentials Flow", retryAnalyzer = Retry.class, alwaysRun = true, groups = "smoke")
    public void openUDCWebsite() {
        try {
              openURL(Base.getProperty().getProperty("application"), true);
              click(LoginPage.visitWebsiteButton, "Clicked On Visit Website Button");

              // My Profile -> Dashboard Page
              enterCharacter(LoginPage.emailButton, "brampton@ultimatedrivers.ca", "Email Submitted");
              enterCharacter(LoginPage.passwordButton, "Mehta@12345", "Password Submitted");
              click(LoginPage.LoginButton, "Login Button Clicked");

              // UI changes so this part is removed (Signature)
              // click(LoginPage.EditHomepage, "Clicked");
              // click(LoginPage.SelectSignOnHomePage, "Selected Signature");
              // click(LoginPage.SelectSignFormat, "Format checkbox selected");
              // click(LoginPage.SubmitSignature, "Submitting Signature");
              // click(LoginPage.crossButton, "Close button clicked");

              click(LoginPage.ClickRemoveProfilePhoto, "Removed Profile Photo");
              Thread.sleep(1000);
              click(LoginPage.ClickEditProfilePhoto, "Clicked on Edit Photo");

              // Added safeClick in CommonFunctionsWeb Page for JSExecutor if it fails (no matter the Button is hidden or not appearing properly)
              // safeClick(LoginPage.SubmitButton1, "Submit clicked with Safe Button");



              Thread.sleep(4000);
              // enterCharacter(LoginPage.mobilenumber, phoneNo, "input number");
              // ConnectionManagerPostgreSQL.connectToDatabasePostgreSQL();
              // enterCharacter(LoginPage.inputotp, LoginPage.getOtpFromPostgreSQL("SELECT * FROM otp_logs WHERE mobile_no = '" + phoneNo + "' ORDER BY created_on DESC;\n","otp"), "Input box for otp");
              // click(LoginPage.submitLoginButton,"Submit Login Button");
              // isElementDisplayed(LoginPage.surpriseMePopTitle,"Surprise Me Pop Up Title Image");
        } catch (Exception e) {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description());
        } finally {
            ConnectionManagerMySQL.closeConnectionDatabaseMySQL();
        }
    }

    @Test(description = "UDC API | Login & Sign Up Flow | LoginSignUp_HLS_001_TC_002 : Validating Email and Password", alwaysRun = true, enabled = false, groups = "sanity")
    public void hitGetAPIForAvatarOnCocaColaHomePage() {
        try {
//            String requestUrl = "https://api-jiab-staging.infinitelocus.com/api/v1/avatars/";
//          AvatarHomePage avatarHomePage = callApi(RefactoredRestAssuredHelper.HTTPRequestType.GET, null, null, requestUrl, null, null, null, 0, 200, "status", AvatarHomePage.class);
//          compareAndLogNotNull(avatarHomePage, "Response Check : Not Null");
//          compareAndLog(avatarHomePage.status, 200, "Response Status Code Check");
//          compareAndLog(avatarHomePage.message, "Request successful", "Response Message Check");
        } catch (Exception e) {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description() + e.getMessage());
        }
    }
}
