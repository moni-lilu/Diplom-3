package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HeaderClass {

    public String buttonOfficeId = "//p[text()='Личный Кабинет']";

    public void clickToLinkOffice() {
        $(byXpath(buttonOfficeId)).shouldHave(Condition.visible).click();
    }
}
