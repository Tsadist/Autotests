package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    Browser browser = AqualityServices.getBrowser();
    File setting = new File("./src/test/resources/setting.json");
    String url;

    @BeforeMethod
    public void before() {
        try {
             url = (String) new JsonSettingsFile(setting).getValue("/url");
        } catch (IOException e) {
            AqualityServices.getLogger().error("Проблемы с чтением URL из файла конфигурации");
        }
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @AfterSuite
    public void after() {
        browser.quit();
    }
}
