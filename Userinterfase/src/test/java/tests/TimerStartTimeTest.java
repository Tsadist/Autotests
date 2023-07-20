package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.AuthorizationForm;

public class TimerStartTimeTest extends BaseTest {

    private final static String FORMAT_TIME = "00:00:00";

    @Test
    public void test() {

        HomePage homePage = new HomePage();

        Assert.assertTrue(homePage.state().isDisplayed(), "Домашняя страница не была открыта");
        homePage.clickLinkNextPage();

        AuthorizationForm authorizationForm = new AuthorizationForm();

        Assert.assertTrue(authorizationForm.state().isDisplayed(), "Страница с первой карточкой не была открыта");
        Assert.assertEquals(FORMAT_TIME, authorizationForm.getTimeValue(), String.format("Таймер начинается не с %s", FORMAT_TIME));
    }
}
