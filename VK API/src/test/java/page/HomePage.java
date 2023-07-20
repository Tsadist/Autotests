package page;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private final static By UNIQUE_ELEMENT_LOCATOR = By.xpath("//*[@*='VkIdForm']");
    private static final By TEXT_BOX_LOGIN_LOCATOR = By.xpath("//*[@id='index_email']");
    private static final By BUTTON_SIGN_IN_LOCATOR = By.xpath("//*[contains(@*, 'VkIdForm__signInButton')]");

    private final ITextBox TEXT_BOX_LOGIN = getElementFactory().getTextBox(TEXT_BOX_LOGIN_LOCATOR, "TEXT_BOX_EMAIL");
    private final IButton BUTTON_SIGN_IN = getElementFactory().getButton(BUTTON_SIGN_IN_LOCATOR, "BUTTON_SIGN_IN");


    public HomePage() {
        super((UNIQUE_ELEMENT_LOCATOR), "Unique_element");
    }

    public void setLogin(String inputText) {
        TEXT_BOX_LOGIN.sendKeys(inputText);
        BUTTON_SIGN_IN.click();
    }
}
