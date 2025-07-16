package qa.pages;

import org.openqa.selenium.By;
import utilsDatabase.ConnectionManagerMySQL;
import utilsWeb.CommonFunctionsWeb;

public class LoginPage extends CommonFunctionsWeb {
    public static By crossButtonOnSurpriseMePopUp = By.xpath("//*[@id=\"radix-:r0:\"]/div[2]/button");
    public static By profileIconOnLoginPage = By.xpath("//img[@alt='Profile Image']");
    public static By loginButtonOnProfileDropDown = By.xpath("//button[@class='w-full text-left px-4 py-2 hover:bg-gray-50']");

    public static void setCredentialsForMySQL(String dbHost, String dbName, String dbUser, String dbPassword){
        ConnectionManagerMySQL.setDbHost(dbHost);
        ConnectionManagerMySQL.setDbName(dbName);
        ConnectionManagerMySQL.setDbUser(dbUser);
        ConnectionManagerMySQL.setDbPassword(dbPassword);
    }

    public static String getOtpFromMySQL(String query, String columnName){
        return ConnectionManagerMySQL.executeSelectQuery(query).get(0).get(columnName);
    }
}
