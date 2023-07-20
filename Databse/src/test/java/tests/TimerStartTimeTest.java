package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthorizationForm;
import pages.HomePage;
import tests.steps.ActionsWithTableTest;
import tests.testDate.NewRecordInTableTest;

import java.sql.Timestamp;

@Slf4j
public class TimerStartTimeTest extends BaseTest {

    private final static String FORMAT_TIME = "00:00:00";

    @Test()
    public void test() {

        HomePage homePage = new HomePage();

        log.info("Шаг 1, проверка открытия домашней страницы");
        Assert.assertTrue(homePage.state().isDisplayed(),
                "Шаг 1, домашняя страница не была открыта");

        log.info("Шаг 2, Переход на кнопку следующей страницы");
        homePage.clickLinkNextPage();

        AuthorizationForm authorizationForm = new AuthorizationForm();

        log.info("Шаг 3, проверка открытия страницы с первой карточкой");
        Assert.assertTrue(authorizationForm.state().isDisplayed(),
                "Шаг 3, страница с первой карточкой не была открыта");
        log.info("Шаг 4, проверка корректности работы таймера");
        Assert.assertEquals(FORMAT_TIME, authorizationForm.getTimeValue(),
                String.format("Шаг 4, таймер начинается не с %s", FORMAT_TIME));
    }

    @AfterMethod
    public void after(ITestResult result) {

        int statusId = result.getStatus();
        String methodName = result.getTestClass().getRealClass().getName();
        String startTime = new Timestamp(System.currentTimeMillis()).toString();

        log.info("Постусловие, добавление новой записи в таблицу тестов и проверка добавления ");
        Assert.assertTrue(ActionsWithTableTest.isAddRowWithTestDate(
                        NewRecordInTableTest.name,
                        NewRecordInTableTest.projectId,
                        NewRecordInTableTest.sessionId,
                        NewRecordInTableTest.env,
                        statusId, methodName, startTime),
                "Постусловие, новая строка не была добавлена");
    }
}
