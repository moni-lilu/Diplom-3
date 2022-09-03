package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    public String linkEntry = "(//a[text()='Войти'])";

    public SelenideElement getLinkEntry() {
        return $(byXpath(linkEntry)).shouldHave(Condition.visible);
    }
}
