import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import stellarburgers.nomoreparties.site.LoginPage;
import stellarburgers.nomoreparties.site.MainPage;
import stellarburgers.nomoreparties.site.RegisterPage;

import static org.hamcrest.CoreMatchers.containsString;

public class GoToSectionTest {
    private String bun = "Булки";
    private static final String url = "https://stellarburgers.nomoreparties.site/";
    MainPage mainPage = new MainPage();

    @AfterClass
    public static void clearCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Before
    public void setupClass() {
        Configuration.browser = "firefox";
    }

    @Test
    public void shouldDisplayBunsTest() throws InterruptedException {
        Selenide.open(url);
        mainPage.getTubSouses().click();
        mainPage.getTubBuns().click();
        MatcherAssert.assertThat(mainPage.getCurrentTubClass().getText(), containsString("Булки"));
    }

    @Test
    public void shouldDisplaySousesTest() {
        Selenide.open(url);
        mainPage.getTubSouses().click();
        MatcherAssert.assertThat(mainPage.getCurrentTubClass().getText(), containsString("Соусы"));
    }

    @Test
    public void shouldDisplayFillingsTest() {
        Selenide.open(url);
        mainPage.getTubFillings().click();
        MatcherAssert.assertThat(mainPage.getCurrentTubClass().getText(), containsString("Начинки"));

    }

}
