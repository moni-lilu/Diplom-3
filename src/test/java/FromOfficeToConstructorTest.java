import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.nomoreparties.site.LoginPage;
import stellarburgers.nomoreparties.site.MainPage;
import stellarburgers.nomoreparties.site.ProfilePage;
import stellarburgers.nomoreparties.site.RegisterPage;

public class FromOfficeToConstructorTest {

    private static final String url = "https://stellarburgers.nomoreparties.site/";
    private static String name = "James";
    private static String email = "bond-" + RandomStringUtils.randomNumeric(20) + "@gmail.com";
    private static String password = "pass007";
    static MainPage main;

    @BeforeClass
    public static void browserPreparationAndUserRegistration() throws InterruptedException {
        Configuration.browser = "firefox";
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = registerPage.EnterDataInToRegistrationForm(name, email, password);
        main = loginPage.EnterDataInToLoginForm(email, password);
    }

    @AfterClass
    public static void clearCookies() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldGoToMainPageByPressedLogoTest() {
        ProfilePage profile = main.clickToLinkOffice();
        profile.clickToLogo();
        String currentUrl = WebDriverRunner.currentFrameUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", currentUrl);
    }

    @Test
    public void shouldGoToMainPageByPressedConstructorTest() {
        ProfilePage profile = main.clickToLinkOffice();
        profile.clickConstructor();
        String currentUrl = WebDriverRunner.currentFrameUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", currentUrl);
    }

}
