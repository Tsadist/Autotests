package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.AuthorizationForm;

public class CookiesTest extends BaseTest {

    @Test
    public void test() {
        HomePage homePage = new HomePage();

        Assert.assertTrue(homePage.state().isDisplayed(), "Домашняя страница не была открыта");
        homePage.clickLinkNextPage();

        AuthorizationForm authorizationForm = new AuthorizationForm();

        Assert.assertTrue(authorizationForm.state().isDisplayed(), "Страница с первой карточкой не была открыта");
        authorizationForm.closeCookies();

        Assert.assertTrue(authorizationForm.isCookiesClosed(), "Форма с Куками не была закрыта");
    }
}
