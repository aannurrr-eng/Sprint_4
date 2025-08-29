package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class OrderPage extends BasePage{

    //поле Имя
    private By nameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //поле Фамилия
    private By surnameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //поле Адрес
    private By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //поле Станция метро
    private By metroInput = By.xpath(".//input[contains(@placeholder, 'Станция метро')]");
    //Список станций метро
    private By metroList = By.xpath(".//div[@class='select-search__select']");
    //поле Телефон
    private By phoneInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //кнопка Далее
    private By nextBtn = By.xpath(".//button[text()='Далее']");

    //поле Когда привезти самокат
    private By dateInput = By.xpath(".//input[contains(@placeholder, 'Когда привезти')]");
    //Календарь
    private By datePicker = By.className("react-datepicker__tab-loop");
    //поле Срок аренды
    private By rentalPeriodInput = By.xpath(".//div[contains(text(), 'Срок аренды')]");
    //список сроков аренды
    private By rentalPeriodList = By.className("Dropdown-menu");
    //Поле комментарий
    private By commentInput = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    //Кнопка Заказать
    private By orderBtn = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Кнопка Да
    private By yesBtn = By.xpath(".//button[text()='Да']");
    //Всплывающее окно об успешном создании заказа
    private By sucsessWindow = By.xpath(".//div[contains(text(),'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void fillName(String name)
    {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void fillSurname(String surname)
    {
        driver.findElement(surnameInput).clear();
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void fillAddress(String address)
    {
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }

    public void fillMetro(String metro)
    {
        driver.findElement(metroInput).click();
        WebElement el = driver.findElement(metroList).findElement(By.xpath(String.format(".//button[.//text()='%s']", metro)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);
        el.click();
    }

    public void fillPhone(String phone)
    {
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void fillDate(LocalDate date)
    {
        if (date.isBefore(LocalDate.now()))
            return;

        driver.findElement(dateInput).click();
        WebElement divWithDatePicker = driver.findElement(datePicker);
        String month;
        switch (date.getMonthValue()) {
            case 1:
                month = "январь";
                break;
            case 2:
                month = "февраль";
                break;
            case 3:
                month = "март";
                break;
            case 4:
                month = "апрель";
                break;
            case 5:
                month = "май";
                break;
            case 6:
                month = "июнь";
                break;
            case 7:
                month = "июль";
                break;
            case 8:
                month = "август";
                break;
            case 9:
                month = "сентябрь";
                break;
            case 10:
                month = "октябрь";
                break;
            case 11:
                month = "ноябрь";
                break;
            default:
                month = "декабрь";
                break;
        }
        String monthYear = month + " " + String.valueOf(date.getYear());
        while (!divWithDatePicker.findElement(By.className("react-datepicker__current-month")).getText().equals(monthYear)) {
            divWithDatePicker.findElement(By.xpath(".//button[text()='Next Month']")).click();
        }
        List<WebElement> els =  divWithDatePicker.findElements(By.xpath(String.format(".//div[text()='%s']", String.valueOf(date.getDayOfMonth()) )));
        WebElement el = (date.getDayOfMonth() < 20 ? els.get(0) : els.get(els.size() - 1));
        el.click();
    }

    public void fillRentalPeriod(int period)
    {
        driver.findElement(rentalPeriodInput).click();
        driver.findElement(rentalPeriodList).findElements(By.xpath(".//*[@class='Dropdown-option']")).get(period).click();
    }

    public void fillColour(String colour)
    {
        driver.findElement(By.id(colour)).click();
    }

    public void fillComment(String comment)
    {
        driver.findElement(commentInput).clear();
        driver.findElement(commentInput).sendKeys(comment);
    }

    public boolean isSucsessWindowVisible()
    {
        return driver.findElement(sucsessWindow).isDisplayed();
    }

    public void clickNextButton()
    {
        driver.findElement(nextBtn).click();
    }

    public void clickOrderButton()
    {
        driver.findElement(orderBtn).click();
    }

    public void clickYesButton()
    {
        driver.findElement(yesBtn).click();
    }

}
