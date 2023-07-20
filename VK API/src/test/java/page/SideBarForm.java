package page;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBarForm extends Form {

    private final static By UNIQUE_ELEMENT_LOCATOR = By.xpath("//*[@id='side_bar']");
    private final static By LINK_MY_PAGE_LOCATOR = By.xpath("//*[@id='l_pr']");

    private final ILink LINK_MY_PAGE = getElementFactory().getLink(LINK_MY_PAGE_LOCATOR, "LINK_MY_PAGE");

    public SideBarForm() {
        super((UNIQUE_ELEMENT_LOCATOR), "Unique_element");
    }

    public void clickMyPage(){
        LINK_MY_PAGE.click();
    }


}
