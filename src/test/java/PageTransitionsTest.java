import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import stellarburgers.nomoreparties.site.*;

@RunWith(Parameterized.class)
public class PageTransitionsTest {
    private final String path;
    private static final String url = "https://stellarburgers.nomoreparties.site/";
    private static String name = "James";
    private static String email = "bond-" + RandomStringUtils.randomNumeric(20) + "@gmail.com";
    private static String password = "pass007";
    static MainPage main = new MainPage();


    public PageTransitionsTest(String path) {
        this.path = path;
    }

    @Parameterized.Parameters
    public static Object[] dataForRegistration() {
        return new Object[]{
                "",
                "feed",
                "account/order-history"
        };
    }

    @AfterClass
    public static void clearCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeClass
    public static void browserPreparationAndUserRegistration() throws InterruptedException {
        Configuration.browser = "firefox";
        Selenide.open(url + "register");
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = registerPage.EnterDataInToRegistrationForm(name, email, password);
        Thread.sleep(1000);
        main = loginPage.EnterDataInToLoginForm(email, password);
        Thread.sleep(1000);
    }

    @Test
    public void shouldGoToOfficePageTest() throws InterruptedException {
        if (path.equals("account/order-history")) {
            Selenide.open(url);
            ProfilePage profilePage = main.clickToLinkOffice();
            profilePage.getOrderHistory().click();
        } else {
            Selenide.open(url + path);
        }
        (new HeaderClass()).clickToLinkOffice();
        String currentUrl = WebDriverRunner.currentFrameUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", currentUrl);
    }

}
