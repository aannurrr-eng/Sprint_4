package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private final String HOME_URL = "https://qa-scooter.praktikum-services.ru/";

    //Кнопки со стрелочкой и вопросами
    private By questionBtns = By.className("accordion__button");
    //Ответы
    private By answers = By.xpath(".//div[@class='accordion__panel']/p");

    //Кнопка Заказать на header
    private By orderInHeaderBtn = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //Кнопка Заказать внизу страницы
    private By orderInBottomBtn = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //Кнопка Статус заказа
    private By statusBtn = By.className("Header_Link__1TAG7");
    //Поле для ввода номера заказа
    private By statusInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    //Кнопка Go для проверки статуса заказа
    private By goBtn = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    //Логотип самоката
    private By logoScooter = By.xpath(".//img[@alt='Scooter']");
    //Логотип Яндекс
    private By logoYandex = By.xpath(".//img[@alt='Yandex']");

    //Кнопка Принять куки
    private By cookieBtn = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    public void openPage()
    {
        driver.get(HOME_URL);
        waitForLoading(cookieBtn);
        driver.findElement(cookieBtn).click();
    }

    public String questionText(int index)
    {
        waitForLoading(questionBtns);
        return driver.findElements(questionBtns).get(index).getText();
    }

    public String answerText(int index)
    {
        return driver.findElements(answers).get(index).getAttribute("textContent");
    }

    public void clickQuestion(int index)
    {
        WebElement question = driver.findElements(questionBtns).get(index);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", question);
        waitForLoading(question);
        question.click();
    }

    public boolean isAnswerVisible(int index)
    {
        WebElement answer = driver.findElements(answers).get(index);
        waitForLoading(answer);
        return answer.isDisplayed();
    }

    public void clickOrderInHeaderBtn()
    {
        driver.findElement(orderInHeaderBtn).click();
    }

    public void clickOrderInBottomBtn()
    {
        WebElement btn = driver.findElement(orderInBottomBtn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", btn);
        btn.click();
    }

    public void clickStatusBtn()
    {
        waitForLoading(statusBtn);
        driver.findElement(statusBtn).click();
    }

    public void setStatusNumber(String number)
    {
        waitForLoading(statusInput);
        driver.findElement(statusInput).clear();
        driver.findElement(statusInput).sendKeys(number);
    }

    public void clickGoBtn()
    {
        waitForLoading(goBtn);
        driver.findElement(goBtn).click();
    }

    public void clickLogoScooter()
    {
        driver.findElement(logoScooter).click();
    }

    public void clickLogoYandex()
    {
        driver.findElement(logoYandex).click();
    }

    public boolean isHomePage()
    {
        return driver.getCurrentUrl().equals(HOME_URL);
    }

}
