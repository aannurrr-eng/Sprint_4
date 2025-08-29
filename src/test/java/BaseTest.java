import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import page.MainPage;
import steps.MainPageSteps;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected MainPageSteps mainPageSteps;

    @Before
    public void startUp()
    {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome"))
        {
            startBrowserChrome();
        }
        else if (browser.equals("firefox"))
        {
            startBrowserFirefox();
        }
        mainPage = new MainPage(driver);
        mainPageSteps = new MainPageSteps(mainPage);
        mainPage.openPage();
    }

    private void startBrowserFirefox()
    {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        String firefoxPath = System.getenv("FIREFOX_PATH");
        if (!firefoxPath.isEmpty()) {
            options.setBinary(firefoxPath);
        }
        driver = new FirefoxDriver(options);
    }

    private void startBrowserChrome()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public  void tearDown()
    {
        driver.quit();
    }

}
