package page;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {

    private final static String REGEX_FOR_ID_LEFT_MENU = "/albums";
    private final static int NUMBER_SUBSTRING_CONTAINS_ID = 1;
    private final static String POST_TEXT_LOCATOR_TEMPLATE = "//*[@id='post%s_%d']//*[contains(@class, 'wall_post_text')]";
    private final static String POST_AUTHOR_LOCATOR_TEMPLATE = "//*[@id='post%s_%d']//*[contains(@class, 'PostHeaderInfo ')]//a[contains(@class, 'author')]";

    private final static By UNIQUE_ELEMENT_LOCATOR = By.xpath("//*[@*='VkIdForm']");
    private final static By LINK_WITH_ID_LOCATOR = By.xpath("//*[@id='l_ph']//*[contains(@class, 'LeftMenu__itemLink')]");

    private final ILink LINK_WITH_ID = getElementFactory().getLink(LINK_WITH_ID_LOCATOR, "Link photos with id");

    private static String idProfile;

    public MyProfilePage() {
        super((UNIQUE_ELEMENT_LOCATOR), "Unique_element");
    }

    public String getPostText(Long postId) {
        ILabel postText = getElementFactory()
                .getLabel(By.xpath(String
                        .format(POST_TEXT_LOCATOR_TEMPLATE,
                                idProfile,
                                postId)), "Text of said post");
        return postText.getText();
    }

    public String getPostAuthor(Long postId) {
        ILink postAuthor = getElementFactory()
                .getLink(By.xpath(String
                        .format(POST_AUTHOR_LOCATOR_TEMPLATE,
                                idProfile,
                                postId)), "Author of said post");

        return postAuthor.getAttribute("data-from-id");
    }

    public String getIdProfile() {
        idProfile = LINK_WITH_ID.getHref().split(REGEX_FOR_ID_LEFT_MENU)[NUMBER_SUBSTRING_CONTAINS_ID];
        return idProfile;
    }
}
