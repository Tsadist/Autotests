package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationForm extends Form {

    private static final By TIMER = By.xpath("//*[contains(@class, 'timer timer--white timer--center')]");
    private static final By UNIQUE_ELEMENT = By.xpath("//a[contains(@class, 'login-form__terms-conditions')]");

    private final ILabel timer = getElementFactory().getLabel(TIMER, "Timer");

    public AuthorizationForm() {
        super(UNIQUE_ELEMENT, "Unique_element");
    }

    public String getTimeValue() {
        return timer.getText();
    }


}
