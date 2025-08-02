package udc.pages;

import utilsWeb.CommonFunctionsWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Dashboard extends CommonFunctionsWeb {
    public static By ViewTaskButton = By.xpath("//button[normalize-space()='View Tasks']");
    public static By AllLocationFilter = By.xpath("//button[normalize-space()='All Locations']");
    public static By SearchLocation = By.xpath("//input[@placeholder='Search for a location']");
    public static By checkboxSelection = By.xpath("//input[@type='checkbox' and contains(@class, 'cursor-pointer')]");
    public static By selectLocation = By.xpath("//button[normalize-space()='All Locations']");
    public static By calendarFilterButton = By.xpath("//button[.//img[@alt='filter'] and contains(text(), 'Aug')]");
    public static By CalendarYearButton = By.xpath("//button[normalize-space()='Year']");
}
