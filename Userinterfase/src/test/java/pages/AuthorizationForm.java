package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.enums.DomainPointOneCard;

import java.util.Map;

public class AuthorizationForm extends Form {

    private final static String domainSecondLevelTemplate = "//*[contains(@class, 'dropdown__list-item') and text()='%s']";
    private final static String KEY_PASSWORD = "password";
    private final static String KEY_EMAIL = "email";
    private final static String KEY_DOMAIN = "domain";

    private final ITextBox textEmail = getElementFactory().getTextBox(By.xpath("//*[contains(@placeholder, 'Your email')]"), "Email_input_field");
    private final ITextBox textPassword = getElementFactory().getTextBox(By.xpath("//*[contains(@placeholder, 'Choose Password')]"), "Password_input_field");
    private final ITextBox textDomain = getElementFactory().getTextBox(By.xpath("//*[contains(@placeholder, 'Domain')]"), "Domain_input_field");
    private final ILabel domainSecondLevelList = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'dropdown__header')]"), "Domain_point");
    private final ICheckBox acceptTerms = getElementFactory().getCheckBox(By.xpath("//*[contains(@class, 'checkbox__label')]"), "Accept_the_terms");
    private final IButton buttonNextLoginForm = getElementFactory().getButton(By.xpath("//*[contains(text(), 'Next')]"), "Button_Next_login_form");
    private final IButton helpFormCloseButton = getElementFactory().getButton(By.xpath("//*[contains(@class, 'help-form__send-to-bottom-button')]"), "Help_form_close_button");
    private final IButton cookiesCloseButton = getElementFactory().getButton(By.xpath("//*[contains(@class, 'cookies')]//*[contains(text(), 'Not really')]"), "Cookies_form_close");
    private final ILabel timer = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'timer timer--white timer--center')]"), "Timer");

    private final By helpFormIsHiddenLocator =  By.xpath("//*[contains(@class, 'help-form is-hidden')]");
    private final By cookiesFormLocator =  By.xpath("//*[contains(@class, 'cookies')]");


    public AuthorizationForm() {
        super(By.xpath("//a[contains(@class, 'login-form__terms-conditions')]"), "Unique_element");
    }

    public void passAuthorization(Map<String, String> dateAuthorization) {
        textEmail.clearAndType(dateAuthorization.get(KEY_EMAIL));
        textPassword.clearAndType(dateAuthorization.get(KEY_PASSWORD));
        textDomain.clearAndType(dateAuthorization.get(KEY_DOMAIN));
        System.out.println();
    }

    public void selectDomainSecondLevel(DomainPointOneCard nameElement) {
        domainSecondLevelList.clickAndWait();
        getElementFactory()
                .getButton(By.xpath(String.format(domainSecondLevelTemplate, nameElement.getValue())), "Domain_point_element")
                .click();
    }

    public void acceptTerms() {
        if(!acceptTerms.isChecked()){
            acceptTerms.check();
        }
    }

    public void clickButtonNext() {
        buttonNextLoginForm.click();
    }

    public void closeHelpForm() {
        helpFormCloseButton.click();
    }

    public boolean isHelpFormClosed() {
        return getElementFactory()
                .findElements(helpFormIsHiddenLocator, "Checked_help_form_close", ElementType.LABEL)
                .size() == 1;
    }

    public void closeCookies() {
        cookiesCloseButton.click();
    }

    public boolean isCookiesClosed() {
        return getElementFactory()
                .findElements(cookiesFormLocator, "Cookies_form_locator", ElementType.LABEL)
                .size() == 0;
    }

    public String getTimeValue() {
        return timer.getText();
    }
}
