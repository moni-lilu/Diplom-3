package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public SelenideElement inputEmail = $(byXpath("(//input[contains(@class, 'text input__textfield text_type_main-default')])[1]"));
    public SelenideElement inputPassword = $(byXpath("(//input[contains(@class, 'text input__textfield text_type_main-default')])[2]"));
    private SelenideElement buttonLogin = $(byXpath("//button[contains(@class, 'button_button__33qZ0')]"));
    private SelenideElement linkEntry = $(byXpath("html/body/div/div/main/div/h2"));

    public MainPage EnterDataInToLoginForm(String email, String password) {
        this.inputEmail.setValue(email);
        this.inputPassword.setValue(password);
        this.buttonLogin.click();
        return new MainPage();
    }

}
