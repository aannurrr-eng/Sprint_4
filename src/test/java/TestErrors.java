import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.OrderPage;
import steps.OrderPageSteps;

import java.time.LocalDate;

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

    private final LocalDate date;
    private final String comment;

    public TestErrors(String name, String surname, String address, String metro, String phone, LocalDate date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "{0} {1}")
    public static Object[][] getData()
    {
        return new Object[][]{
                {"q", "q", "q", "", "898", LocalDate.now().minusDays(1), "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"},
                {"ф", "В", "по", "", "ы", LocalDate.now().minusDays(5), "11111111111111111111111111111111111111111111111111111111111111111111111111"}
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
        errorsOnFirstPageTest();
        orderPageSteps.setCorrectUserData();
        orderPage.clickNextButton();
        errorsOnSecondPageTest();
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

    public void errorsOnSecondPageTest()
    {
        orderPage.fillDate(date);
        orderPage.fillComment(comment);
        orderPage.clickOrderButton();
        assertTrue("Нет сообщения об ошибке в дате", orderPage.isDateErrorVisible());
        assertTrue("Нет сообщения об ошибке в сроке аренды", orderPage.isRentalPeriodErrorVisible());
        assertTrue("Нет сообщения об ошибке в цвете", orderPage.isColourErrorVisible());
        assertTrue("Нет сообщения об ошибке в комментарии", orderPage.isCommentErrorVisible());
    }

}
