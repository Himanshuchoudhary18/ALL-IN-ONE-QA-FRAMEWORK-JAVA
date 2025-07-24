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
    public static By EditHomepage = By.xpath("//button[normalize-space()='Edit']");
    public static By SelectSignOnHomePage = By.xpath("//input[@name='signatureFont' and @checked]");
    public static By SelectSignFormat = By.xpath("//input[@type='radio' and @name='signatureFont']");
    public static By SubmitSignature = By.xpath("//button[@type='submit' and @form='selectSignatureForm' and text()='Select']");
    public static By crossButton = By.xpath("//span[contains(@class, 'absolute')]");
    public static By SubmitButton1 = By.id("SubmitButton");
    public static By SubmitButton2 = By.name("public static By SubmitButton = By.id(\"SubmitButton\");");



    public static String getOtpFromPostgreSQL(String query, String columnName){
        return ConnectionManagerPostgreSQL.executeSelectQuery(query).get(0).get(columnName);
    }
}
