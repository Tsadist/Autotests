package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private final ILink linkNextPage = getElementFactory().getLink(By.partialLinkText("HERE"), "Link_next_page");

    public HomePage() {
        super(By.xpath("//*[contains(text(), 'welcome')]"), "Unique_element");
    }

    public void clickLinkNextPage() {
        linkNextPage.click();
    }
}
