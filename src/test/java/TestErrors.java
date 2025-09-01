import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.OrderPage;
import steps.OrderPageSteps;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestErrors extends BaseTest {
    private OrderPage orderPage;
    private OrderPageSteps orderPageSteps;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;

    public TestErrors(String name, String surname, String address, String metro, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters(name = "{0} {1}")
    public static Object[][] getData()
    {
        return new Object[][]{
                {"q", "q", "q", "", "898"},
                {"ф", "В", "по", "", "ы"}
        };
    }

    @Before
    public void startUp()
    {
        super.startUp();
        orderPage = new OrderPage(driver);
        orderPageSteps = new OrderPageSteps(orderPage);
    }

    @Test
    public void errorsTest()
    {
        mainPage.clickOrderInHeaderBtn();
        // проверить наличие сообщений об ошибках на первой странице
        errorsOnFirstPageTest();
        orderPageSteps.setCorrectUserData();
        orderPage.clickNextButton();
        // TODO: проверить наличие сообщений об ошибках на второй странице
    }

    public void errorsOnFirstPageTest()
    {
        orderPageSteps.setUserData(name, surname, address, metro, phone);
        orderPage.clickNextButton();
        assertTrue("Нет сообщения об ошибке в имени", orderPage.isNameErrorVisible());
        assertTrue("Нет сообщения об ошибке в фамилии", orderPage.isSurnameErrorVisible());
        assertTrue("Нет сообщения об ошибке в адресе", orderPage.isAddressErrorVisible());
        assertTrue("Нет сообщения об ошибке в станции метро", orderPage.isMetroErrorVisible());
        assertTrue("Нет сообщения об ошибке в номере телефона", orderPage.isPhoneErrorVisible());
    }

}
