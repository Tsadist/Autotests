package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private static final By LINK_NEXT_PAGE = By.partialLinkText("HERE");
    private static final By UNIQUE_ELEMENT = By.xpath("//*[contains(text(), 'welcome')]");

    private final ILink linkNextPage = getElementFactory().getLink(LINK_NEXT_PAGE, "Link_next_page");

    public HomePage() {
        super (UNIQUE_ELEMENT, "Unique_element");
    }

    public void clickLinkNextPage() {
        linkNextPage.click();
    }
}
