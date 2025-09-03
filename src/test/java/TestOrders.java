import constants.Colour;
import constants.RentalPeriod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.OrderPage;
import steps.OrderPageSteps;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrders extends BaseTest {
    private OrderPage orderPage;
    private OrderPageSteps orderPageSteps;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;

    private final LocalDate date;
    private final int rentalPeriod;
    private final String colour;
    private final String comment;

    public TestOrders(String name, String surname, String address, String metro, String phone, LocalDate date, int rentalPeriod, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "{0} {1}")
    public static Object[][] getData()
    {
        return new Object[][]{
                {"Ирина", "Кутузова", "Москва1", "Тропарёво", "89898887766", LocalDate.now().plusDays(1), RentalPeriod.DAYS3, Colour.BLACK, "Коммент1"},
                {"Иван", "Васильев", "Москва2", "Румянцево", "+79098887764", LocalDate.now().plusDays(5), RentalPeriod.DAY1, Colour.GRAY, "Коммент2"}
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
    public void orderInHeaderTest()
    {
        mainPage.clickOrderInHeaderBtn();
        orderPageSteps.makeOrder(name, surname, address, metro, phone, date, rentalPeriod, colour, comment);
        assertTrue("Не удается оформить заказ при клике на кнопку Заказать в заголовке", orderPage.isSucsessWindowVisible());
    }

    @Test
    public void orderInBottomTest()
    {
        mainPage.clickOrderInBottomBtn();
        orderPageSteps.makeOrder(name, surname, address, metro, phone, date, rentalPeriod, colour, comment);
        assertTrue("Не удается оформить заказ при клике на кнопку Заказать внизу страницы", orderPage.isSucsessWindowVisible());
    }

}
