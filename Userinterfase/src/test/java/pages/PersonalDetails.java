package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetails extends Form {

    public PersonalDetails() {
        super(By.xpath("//*[contains(@class, 'personal-details__content')]"), "Unique_element");
    }
}
