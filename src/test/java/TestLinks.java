import browser.Browser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLinks extends BaseTest{

    private final String YANDEX_URL = "https://dzen.ru/?yredirect=true";

    private Browser browser;

    @Before
    public void startUp()
    {
        super.startUp();
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
        assertEquals("При клике на логотип Яндекс не открылась новая вкладка", 2, browser.countOfTabs());
        browser.switchToTab(1, YANDEX_URL);
        assertTrue("При клике на логотип Яндекс не загрузилась главная страница Яндекс", driver.getCurrentUrl().equals(YANDEX_URL));
    }

}
