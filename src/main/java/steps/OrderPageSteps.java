package steps;

import page.OrderPage;

import java.time.LocalDate;

public class OrderPageSteps {
    private OrderPage orderPage;

    public OrderPageSteps(OrderPage orderPage) {
        this.orderPage = orderPage;
    }

    public void setUserData(String name, String surname, String address, String metro, String phone)
    {
        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.fillMetro(metro);
        orderPage.fillPhone(phone);
    }
    public void setRentalData(LocalDate date, int rentalPeriod, String colour, String comment)
    {
        orderPage.fillDate(date);
        orderPage.fillRentalPeriod(rentalPeriod);
        orderPage.fillColour(colour);
        orderPage.fillComment(comment);
    }
    public void makeOrder(String name, String surname, String address, String metro, String phone,
                          LocalDate date, int rentalPeriod, String colour, String comment)
    {
        setUserData(name, surname, address, metro, phone);
        orderPage.clickNextButton();
        setRentalData(date, rentalPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
    }
}
