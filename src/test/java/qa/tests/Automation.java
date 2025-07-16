package qa.tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.Retry;
import qa.pages.LoginPage;
import qa.responseDto.AvatarHomePage;
import utilities.Base;
import utilsApi.RefactoredRestAssuredHelper;

import static utilsWeb.CommonFunctionsWeb.*;

@Test(description = "Sign Up and Login Flow for Coca Cola Website")
public class Automation extends Base {
    @DataProvider(name = "LoginCredentials")
    public Object[][] createData1() {
        return new Object[][]{
                {"7206391749", 1234}
        };
    }

    @Test(description = "CocaCola Web | Login & Sign Up Flow | LoginSignUp_HLS_001_TC_001 : Home Page Validations and Invalid Login Credentials Flow", dataProvider = "LoginCredentials", retryAnalyzer = Retry.class, alwaysRun = true, enabled = true, groups = "smoke")
    public void openCocaColaWebsite(String phoneNo, int otp) {
        try {
            openURL(Base.getProperty().getProperty("application"), true);
            click(LoginPage.crossButtonOnSurpriseMePopUp, "Close Button on Surprise Me Pop Up");
            click(LoginPage.profileIconOnLoginPage, "Profile Icon");
            click(LoginPage.loginButtonOnProfileDropDown, "Select Login Option");
            System.out.println("Verifying Data Provider Data --> Phone No : " + phoneNo + " and OTP : " + otp);
        } catch (Exception e) {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description());
        }
    }

    @Test(description = "CocaCola API | Login & Sign Up Flow | LoginSignUp_HLS_001_TC_002 : Validate GET API for getting avatar on Home Page", alwaysRun = true, enabled = true, groups = "sanity")
    public void hitGetAPIForAvatarOnCocaColaHomePage() {
        try {
            String requestUrl = "https://api-jiab-staging.infinitelocus.com/api/v1/avatars/";
            AvatarHomePage avatarHomePage = callApi(RefactoredRestAssuredHelper.HTTPRequestType.GET, null, null, requestUrl, null, null, null, 0, 200, "status", AvatarHomePage.class);
            compareAndLogNotNull(avatarHomePage, "Response Check : Not Null");
            compareAndLog(avatarHomePage.status, 200, "Response Status Code Check");
            compareAndLog(avatarHomePage.message, "Request successful", "Response Message Check");
        } catch (Exception e) {
            testLevelReport.get().log(Status.FAIL, "Test Execution Failed for : " + getClass().getAnnotation(Test.class).description() + e.getMessage());
        }
    }
}
