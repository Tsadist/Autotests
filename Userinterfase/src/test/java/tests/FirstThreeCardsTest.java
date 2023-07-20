package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.AuthorizationForm;
import pages.PersonalDetails;
import pages.AvatarAndInterests;
import pages.enums.CheckBoxTwoCard;
import pages.enums.DomainPointOneCard;
import utils.AuthenticationData;

public class FirstThreeCardsTest extends BaseTest {

    private final static int COUNT_CHAR_PASSWORD = 10;
    private final static int COUNT_CHAR_EMAIL = 5;
    private final static int COUNT_CHAR_DOMAIN = 3;
    private final static int NUMBER_RANDOM_CHECKBOX = 3;

    @Test
    public void test() {

        HomePage homePage = new HomePage();
        AuthenticationData authenticationData = new AuthenticationData(COUNT_CHAR_PASSWORD, COUNT_CHAR_EMAIL, COUNT_CHAR_DOMAIN);
        String patchFile = System.getProperty("user.dir") + "\\itachi.jpg";

        Assert.assertTrue(homePage.state().isDisplayed(), "Домашняя страница не была открыта");
        homePage.clickLinkNextPage();

        AuthorizationForm authorizationForm = new AuthorizationForm();

        Assert.assertTrue(authorizationForm.state().isDisplayed(), "Страница с первой карточкой не была открыта");
        authorizationForm.passAuthorization(authenticationData.createdPasswordEmailDomain());
        authorizationForm.selectDomainSecondLevel(DomainPointOneCard.randomValues());
        authorizationForm.acceptTerms();
        authorizationForm.clickButtonNext();

        AvatarAndInterests avatarAndInterests = new AvatarAndInterests();

        Assert.assertTrue(avatarAndInterests.state().isDisplayed(), "Страница со второй карточкой не была открыта");
        avatarAndInterests.uploadPhoto(patchFile);
        avatarAndInterests.selectOneCheckBox(CheckBoxTwoCard.Unselect_all);
        avatarAndInterests.selectCheckBoxes(CheckBoxTwoCard.RandomValuesList(NUMBER_RANDOM_CHECKBOX));
        avatarAndInterests.clickNextButton();

        PersonalDetails personalDetails = new PersonalDetails();

        Assert.assertTrue(personalDetails.state().isDisplayed(), "Страница с третьей карточкой не была открыта");
    }
}
