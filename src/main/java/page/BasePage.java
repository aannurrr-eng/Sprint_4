package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait objWait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        objWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    protected void waitForLoading(By sel)
    {
        objWait.until(ExpectedConditions.visibilityOfElementLocated(sel));
    }

    protected void waitForLoading(WebElement el)
    {
        objWait.until(ExpectedConditions.visibilityOf(el));
    }

}
