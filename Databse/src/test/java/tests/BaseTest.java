package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.DriverDB;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

@Slf4j
public class BaseTest {

    private final static Browser browser = AqualityServices.getBrowser();
    private final static File CONFIG_PATH = new File("./src/test/resources/config.json");
    private static String url;

    @BeforeMethod
    public void before() {
        try {
            JsonSettingsFile settingsFile = new JsonSettingsFile(CONFIG_PATH);
            url = (String) settingsFile.getValue("/url");
        } catch (IOException e) {
            log.error("Ошибка при чтении файла config.json  в BaseTest" + e.getMessage());
        }
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @AfterSuite
    public void afterSuite() {
        DriverDB.closeConnection();
        browser.quit();
    }
}
