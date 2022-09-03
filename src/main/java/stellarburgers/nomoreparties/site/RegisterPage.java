package stellarburgers.nomoreparties.site;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    private String inputNameId = "(//input[contains(@class, 'input__textfield')])[1]";
    private String  inputEmailId = "(//input[contains(@class, 'input__textfield')])[2]";
    private String  inputPasswordId = "(//input[contains(@class, 'input__textfield')])[3]";
    private String buttonRegistrationId = "//button[contains(@class, 'button_button__33qZ0')]";
    public String warningIncorrectPasswordId = "input__error";
    public String linkEntry = "(//a[text()='Войти'])";

    public LoginPage EnterDataInToRegistrationForm(String name, String email, String password) throws InterruptedException {
        Thread.sleep(1000);
        $(byXpath(inputNameId)).shouldHave(Condition.visible).setValue(name);
        $(byXpath(inputEmailId)).shouldHave(Condition.visible).setValue(email);
        $(byXpath(inputPasswordId)).shouldHave(Condition.visible).setValue(password);
        $(byXpath(buttonRegistrationId)).shouldHave(Condition.visible).click();
        return new LoginPage();
    }

    public SelenideElement EnterWrongDataInToRegistrationForm(String name, String email, String password) {
        $(byXpath(inputNameId)).setValue(name);
        $(byXpath(inputEmailId)).setValue(email);
        $(byXpath(inputPasswordId)).setValue(password);
        $(byXpath(buttonRegistrationId)).click();
        SelenideElement warningIncorrectPassword = $(byClassName(warningIncorrectPasswordId));
        warningIncorrectPassword.shouldHave(Condition.visible);
        return warningIncorrectPassword;
    }

    public SelenideElement getLinkEntry() {
        return $(byXpath(linkEntry)).shouldHave(Condition.visible);
    }

}
