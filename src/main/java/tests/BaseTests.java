package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    private static final String PATH_TO_CHROME_DRIVER = "chromedriver";
    private static final boolean HEADLESS_ON = false;
    private static final String BASE_URL = "https://stupendous-birth.surge.sh/";

    protected WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(HEADLESS_ON);

        this.driver = new ChromeDriver(options);

        driver.get(BASE_URL);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}

