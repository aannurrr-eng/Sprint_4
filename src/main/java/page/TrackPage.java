package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackPage extends BasePage {

    //Картинка отсутствия заказа
    private By noFoundImage = By.xpath(".//img[@alt='Not found']");

    public TrackPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNoFoundImageDisplayed()
    {
        waitForLoading(noFoundImage);
        return driver.findElement(noFoundImage).isDisplayed();
    }
}
