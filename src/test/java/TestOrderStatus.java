import org.junit.Before;
import org.junit.Test;
import page.TrackPage;

import static org.junit.Assert.assertTrue;

public class TestOrderStatus extends BaseTest{

    private final String WRONG_ORDER = "123";

    private TrackPage trackPage;

    @Before
    public void startUp()
    {
        super.startUp();
        trackPage = new TrackPage(driver);
    }

    @Test
    public void testWrongOrderStatus()
    {
        mainPageSteps.checkStatus(WRONG_ORDER);
        assertTrue("Нет картинки отсутствия заказа", trackPage.isNoFoundImageDisplayed());
    }

}
