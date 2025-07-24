package udc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilsDatabase.ConnectionManagerPostgreSQL;
import utilsWeb.CommonFunctionsWeb;

public class LoginPage extends CommonFunctionsWeb {
    public static By visitWebsiteButton = By.xpath("//button[contains(@class, 'bg-blue-600') and contains(., 'Visit Site')]");
    // Login Page
    public static By emailButton = By.xpath("//input[@name='email']");
    public static By passwordButton = By.name("password");
    public static By LoginButton = By.xpath("//button[@type='submit' and text()='Login']");

    // UI changed (Signature)
    // public static By EditHomepage = By.xpath("//button[normalize-space()='Edit']");
    // public static By SelectSignOnHomePage = By.xpath("//input[@name='signatureFont' and @checked]");
    // public static By SelectSignFormat = By.xpath("//input[@type='radio' and @name='signatureFont']");
    // public static By SubmitSignature = By.xpath("//button[@type='submit' and @form='selectSignatureForm' and text()='Select']");
    // public static By crossButton = By.xpath("//span[contains(@class, 'absolute')]");

    public static By ClickRemoveProfilePhoto = By.xpath("//button[normalize-space()='Remove']");
    public static By ClickEditProfilePhoto = By.xpath("//label[normalize-space(text())='Edit']");

    public static By SubmitButton1 = By.xpath("//button[@class='font-medium py-2 px-4 rounded-lg focus:outline-none focus:shadow-outline text-center flex justify-center items-center w-40 h-12 bg-udc_blue_800 text-white']");


    public static String getOtpFromPostgreSQL(String query, String columnName){
        return ConnectionManagerPostgreSQL.executeSelectQuery(query).get(0).get(columnName);
    }
}
