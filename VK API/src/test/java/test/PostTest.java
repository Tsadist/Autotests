package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.MyProfilePage;
import page.SideBarForm;
import test.steps.APISteps;
import test.steps.UISteps;
import utils.Randomizer;
import utils.VkApiUtils;

import java.util.List;

public class PostTest {

    private final static Browser BROWSER = AqualityServices.getBrowser();
    private final static String CONFIG_PATH = "setting.json";
    private final static String AUTHORIZATION_DATE_PATH = "authorizationDate.json";

    protected static String login;
    protected static String password;

    @BeforeMethod
    public void before() {
        JsonSettingsFile settingsFile = new JsonSettingsFile(CONFIG_PATH);
        JsonSettingsFile authorizationDate = new JsonSettingsFile(AUTHORIZATION_DATE_PATH);

        login = authorizationDate.getValue("/login").toString();
        password = authorizationDate.getValue("/password").toString();

        BROWSER.maximize();
        BROWSER.goTo(String.valueOf(settingsFile.getValue("/defaultUrlUI")));
        BROWSER.waitForPageToLoad();
    }

    @Test
    public void testName() {
        String postText;
        String photoForPostPath = "photo.jpg";
//        String ownerId = "-166420164";
        String typeListLikeObject = "post";
        String textForNewPost = Randomizer.getRandomString();
        String ownerId = "487662589";

        UISteps.authorization(login, password);
        MyProfilePage myProfilePage = UISteps.enterMyPage();

//        myProfilePage.getIdProfile();

        Long newPostId = VkApiUtils.wallPost(ownerId, textForNewPost);

        postText = myProfilePage.getPostText(newPostId);
        Assert.assertEquals(postText, textForNewPost, "Текст в созданном посте не совпадает с проверяемым");

        String photoIdFromPost = APISteps.getPhotoIdFromPost(photoForPostPath);
        VkApiUtils.wallEdit(ownerId, newPostId, Randomizer.getRandomString(), photoIdFromPost);

        postText = myProfilePage.getPostText(newPostId);


//        myProfilePage.getPostAuthor(newPostId);


//        VkApiUtils.createComment(ownerId, newPostId, Randomizer.getRandomString());
//        List <Long> list = VkApiUtils.getListLike(ownerId, typeListLikeObject, newPostId);
//
//        list.forEach(System.out::println);
//        System.out.println(newPostId);
//
//        int delete = VkApiUtils.wallDelete(ownerId, newPostId);
//        System.out.println(delete);

    }

    @AfterSuite
    public void afterSuite() {
        BROWSER.quit();
    }
}
