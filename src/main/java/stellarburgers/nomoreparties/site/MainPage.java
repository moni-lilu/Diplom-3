package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public String buttonOfficeId = "(//p[text()='Личный Кабинет'])";
    public String buttonLoginId = "(//button[text()='Войти в аккаунт'])";
    public String tubBunsId = "//span[text()='Булки']";
    public String tubSaucesId = "//span[text()='Соусы']";
    public String tubFillingsId = "//span[text()='Начинки']";

    public String currentTubClass = "tab_tab_type_current__2BEPc";

    public ProfilePage clickToLinkOffice() {
        $(byXpath(buttonOfficeId)).shouldHave(Condition.visible).click();
        return new ProfilePage();
    }



    public SelenideElement getButtonLogin() {
        return $(byXpath(buttonLoginId)).shouldHave(Condition.visible);
    }

    public SelenideElement getButtonOffice() {
        return $(byXpath(buttonOfficeId)).shouldHave(Condition.visible);
    }
    public SelenideElement getTubBuns() {
        return $(byXpath(tubBunsId)).shouldHave(Condition.visible);
    }
    public SelenideElement getTubSouses() {
        return $(byXpath(tubSaucesId)).shouldHave(Condition.visible);
    }
    public SelenideElement getTubFillings() {
        return $(byXpath(tubFillingsId)).shouldHave(Condition.visible);
    }
    public SelenideElement getCurrentTubClass() {
        return $(byClassName(currentTubClass)).shouldHave(Condition.visible);

    }

}
