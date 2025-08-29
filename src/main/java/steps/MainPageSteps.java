package steps;

import page.MainPage;

public class MainPageSteps {
    private MainPage mainPage;

    public MainPageSteps(MainPage mainPage)
    {
        this.mainPage = mainPage;
    }

    public boolean isCorrectAnswer(int index)
    {
        mainPage.clickQuestion(index);
        return mainPage.isAnswerVisible(index);
    }

    public void checkStatus(String number)
    {
        mainPage.clickStatusBtn();
        mainPage.setStatusNumber(number);
        mainPage.clickGoBtn();
    }
}
