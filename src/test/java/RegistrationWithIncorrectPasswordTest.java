import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stellarburgers.nomoreparties.site.RegisterPage;
import stellarburgers.nomoreparties.site.RegistrationData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.Wait;

@RunWith(Parameterized.class)
public class RegistrationWithIncorrectPasswordTest {
    private final String url = "https://stellarburgers.nomoreparties.site/";
    static String name = "Katalina";
    static String email = "katalina-x@gmail.com";
    private final RegistrationData registrationData;

    public RegistrationWithIncorrectPasswordTest(RegistrationData registrationData) {
        this.registrationData = registrationData;
    }

    @Parameterized.Parameters
    public static Object[] dataForRegistration() {
        return new Object[] {
                new RegistrationData(name, email, ""),
                new RegistrationData(name, email, "12345"),
                new RegistrationData(name, email, null)
        };
    }

    @AfterClass
    public static void clearCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Before
    public void setupClass() throws InterruptedException {
        Thread.sleep(1000);
        Configuration.browser = "firefox";
    }


    @Test
    public void shouldBeWarningIncorrectPasswordTest() throws InterruptedException {
        Thread.sleep(1000);
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        SelenideElement warning = registerPage.EnterWrongDataInToRegistrationForm(registrationData.getName(),
                registrationData.getEmail(),
                registrationData.getPassword());
        warning.shouldHave(text("Некорректный пароль"));
    }

}
