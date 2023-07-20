package page;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {

    private final static By UNIQUE_ELEMENT_LOCATOR = By.xpath("//*[@*='vkc__Auth__pageBox']");
    private static final By TEXT_BOX_PASSWORD_LOCATOR = By.xpath("//*[@*='password']");
    private static final By BUTTON_CONTINUE_LOCATOR = By.xpath("//*[contains(@class,'vkuiButton') and @type='submit']");

    private final ITextBox TEXT_BOX_PASSWORD = getElementFactory().getTextBox(TEXT_BOX_PASSWORD_LOCATOR, "TEXT_BOX_PASSWORD");
    private final IButton BUTTON_CONTINUE = getElementFactory().getButton(BUTTON_CONTINUE_LOCATOR, "BUTTON_CONTINUE");

    public PasswordPage() {
        super(UNIQUE_ELEMENT_LOCATOR, "Unique_element");
    }

    public void setPassword(String inputText) {
        TEXT_BOX_PASSWORD.sendKeys(inputText);
        BUTTON_CONTINUE.click();
    }
}
