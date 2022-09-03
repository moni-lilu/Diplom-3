import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stellarburgers.nomoreparties.site.LoginPage;
import stellarburgers.nomoreparties.site.MainPage;
import stellarburgers.nomoreparties.site.ProfilePage;
import stellarburgers.nomoreparties.site.RegisterPage;

import static org.hamcrest.CoreMatchers.containsString;

public class ExitTest {

    private static final String url = "https://stellarburgers.nomoreparties.site/";
    private static String name = "James";
    private static String email = "bond-" + RandomStringUtils.randomNumeric(20) + "@gmail.com";
    private static String password = "pass007";
    static LoginPage loginPage;

    @BeforeClass
    public static void prepare() throws InterruptedException {
        Configuration.browser = "firefox";
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        loginPage = registerPage.EnterDataInToRegistrationForm(name, email, password);
    }

    @AfterClass
    public static void clearCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldLogoutAndRedirectToLoginPageTest() throws InterruptedException {
        loginPage = makeExit();
        String currentUrl = WebDriverRunner.currentFrameUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/login", currentUrl);
    }

    @Test
    public void fieldEmailShouldBeEmptyTest() throws InterruptedException {
        loginPage = makeExit();
        MatcherAssert.assertThat(loginPage.inputEmail.getValue(), containsString(""));
    }

    @Test
    public void fieldPasswordShouldBeEmptyTest() throws InterruptedException {
        loginPage = makeExit();
        MatcherAssert.assertThat(loginPage.inputPassword.getValue(), containsString(""));
    }

    public LoginPage makeExit() throws InterruptedException {
        MainPage main = loginPage.EnterDataInToLoginForm(email, password);
        ProfilePage profile = main.clickToLinkOffice();
        profile.clickToExit();
        return new LoginPage();
    }

}
