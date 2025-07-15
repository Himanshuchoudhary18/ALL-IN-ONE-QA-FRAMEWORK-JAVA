package utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DriverManager extends Base {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    static Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static Map<String, Method> methods = new HashMap<String, Method>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver dvr) {
        driver.set(dvr);
    }

    public static WebDriver getDriverInstance(String browser, String url) {
        if (driver.get() == null) {
            if (browser.equalsIgnoreCase("CHROME")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                if (property.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }
//                options.addArguments("--no-sandbox");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.setCapability("browserVersion", "stable");
                options.setCapability("browserName", "chrome");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 1); // 1 = Allow, 2 = Block
                options.setExperimentalOption("prefs", prefs);
                setDriver(new ChromeDriver(options));
            } else {
                logger.info("Please Select a valid browser");
                Assert.fail("Unable to launch browser : " + browser);
            }
        }
        setDriver(driver.get());
        return getDriver();
    }

    public static void killDriverInstance() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.set(null);
        }
    }

    public static void setImplicitWait(int time) {
        driver.get().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(time));
    }

    public static void setPageLoadTimeOut(int time) {
        driver.get().manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(time));
    }
}