import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stellarburgers.nomoreparties.site.*;


import static com.codeborne.selenide.Selenide.Wait;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class RegistrationWithCorrectPasswordTest {

    private final static String url = "https://stellarburgers.nomoreparties.site/";
    static String name = "Kristina";
    private final RegistrationData registrationData;

    public RegistrationWithCorrectPasswordTest(RegistrationData registrationData) {
        this.registrationData = registrationData;
    }

    @Parameterized.Parameters
    public static Object[] dataForRegistration() {
        return new Object[] {
                new RegistrationData(name, (RandomStringUtils.randomAlphabetic(20)).toLowerCase() + "@gmail.com", "123456"),
                new RegistrationData(name, (RandomStringUtils.randomAlphabetic(20)).toLowerCase() + "@gmail.com", "1234567"),
                new RegistrationData(name, (RandomStringUtils.randomAlphabetic(20)).toLowerCase() + "@gmail.com", "123456789123456789")
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
    public void shouldBeSuccessRegistrationWithCorrectPasswordTest() throws InterruptedException {
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = registerPage.EnterDataInToRegistrationForm(registrationData.getName(),
                registrationData.getEmail(),
                registrationData.getPassword());
        Thread.sleep(1000);
        MainPage main = loginPage.EnterDataInToLoginForm(registrationData.getEmail(), registrationData.getPassword());
        Thread.sleep(1000);
        ProfilePage profile = main.clickToLinkOffice();
        Thread.sleep(1000);
        SelenideElement fieldEmail = profile.getEmailField();
        MatcherAssert.assertThat(fieldEmail.getValue(), containsString(registrationData.getEmail()));
        profile.clickToExit();
        Thread.sleep(1000);
    }



}
