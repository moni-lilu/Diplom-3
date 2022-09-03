package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    public String buttonExitId = "(//button[text()='Выход'])";
    private String fieldEmailId = "(//input[contains(@class, 'input__textfield')])[2]";
    private String logoId = "html/body/div/div/header/nav/div";
    public String constructorId = "//p[text()='Конструктор']";
    public String orderHistoryId = "//a[text()='История заказов']";

    public SelenideElement getEmailField() {
        SelenideElement emailField = $(byXpath(fieldEmailId)).shouldHave(Condition.visible);
        return emailField;
    }
    public SelenideElement getOrderHistory() {
        SelenideElement orderHistory = $(byXpath(orderHistoryId)).shouldHave(Condition.visible);
        return orderHistory;
    }
    public void clickToExit() {
        $(byXpath(buttonExitId)).shouldHave(Condition.visible).click();
    }

    public void clickToLogo() {
        $(byXpath(logoId)).shouldHave(Condition.visible).click();
    }

    public void clickConstructor() {
        $(byXpath(constructorId)).shouldHave(Condition.visible).click();
    }


}
