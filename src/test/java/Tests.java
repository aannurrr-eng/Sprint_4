import browser.Browser;
import org.junit.Before;
import org.junit.Test;
import page.TrackPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests extends BaseTest{

    private final String WRONG_ORDER = "123";
    private final String YANDEX_URL = "https://dzen.ru/?yredirect=true";

    private TrackPage trackPage;
    private Browser browser;

    @Before
    public void startUp()
    {
        super.startUp();
        trackPage = new TrackPage(driver);
        browser = new Browser(driver);
    }

    @Test
    public void testLogoScooter()
    {
        mainPage.clickLogoScooter();
        assertTrue("При клике на логотип самоката не загрузилась домашняя страница", mainPage.isHomePage());
    }

    @Test
    public void testLogoYandex()
    {
        mainPage.clickLogoYandex();
        assertEquals("При клике на логотип Яндекс не открылась новая вкладка", browser.countOfTabs(), 2);
        browser.switchToTab(1, YANDEX_URL);
        assertTrue("При клике на логотип Яндекс не загрузилась главная страница Яндекс", driver.getCurrentUrl().equals(YANDEX_URL));
    }

    @Test
    public void testWrongOrderStatus()
    {
        mainPageSteps.checkStatus(WRONG_ORDER);
        assertTrue("Нет картинки отсутствия заказа", trackPage.isNoFoundImageDisplayed());
    }

}
