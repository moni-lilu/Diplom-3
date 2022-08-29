import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import stellarburgers.nomoreparties.site.*;

import static org.hamcrest.CoreMatchers.containsString;

public class EntryTest {

    private static final String url = "https://stellarburgers.nomoreparties.site/";
    private static String name = "James";
    private static String email = "bond-" + RandomStringUtils.randomNumeric(20) + "@gmail.com";
    private static String password = "pass007";
    LoginPage loginPage;

    @BeforeClass
    public static void browserPreparationAndUserRegistration() throws InterruptedException {
        Configuration.browser = "firefox";
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        registerPage.EnterDataInToRegistrationForm(name, email, password);
    }

    @AfterClass
    public static void clearCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldEntryByPressLoginButtonOnTheMainPageTest() throws InterruptedException {
        Thread.sleep(300);
        Selenide.open(url);
        MainPage mainPage = new MainPage();
        loginPage = pressButtonEntryToAccount(mainPage.getButtonLogin());
        checkCorrectLogin();
    }

    @Test
    public void shouldEntryByPressPersonalOfficeButtonOnTheMainPageTest() throws InterruptedException {
        Thread.sleep(300);
        Selenide.open(url);
        MainPage mainPage = new MainPage();
        loginPage = pressButtonEntryToAccount(mainPage.getButtonOffice());
        checkCorrectLogin();
    }

    @Test
    public void shouldEntryByPressLoginButtonOnRegistrationFormTest() throws InterruptedException {
        Thread.sleep(300);
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        loginPage = pressButtonEntryToAccount(registerPage.getLinkEntry());
        checkCorrectLogin();
    }

    @Test
    public void shouldEntryByPressLoginButtonOnPasswordRecoveryFormTest() throws InterruptedException {
        Thread.sleep(300);
        Selenide.open(url + "forgot-password");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        loginPage = pressButtonEntryToAccount(forgotPasswordPage.getLinkEntry());
        checkCorrectLogin();
    }

    public LoginPage pressButtonEntryToAccount(SelenideElement loginButton) {
        loginButton.shouldHave(Condition.visible).click();
        return new LoginPage();
    }

    public void checkCorrectLogin() {
        MainPage main = loginPage.EnterDataInToLoginForm(email, password);
        ProfilePage profile = main.clickToLinkOffice();
        SelenideElement fieldEmail = profile.getEmailField();
        MatcherAssert.assertThat(fieldEmail.getValue(), containsString(email));
        profile.clickToExit();
    }

}
