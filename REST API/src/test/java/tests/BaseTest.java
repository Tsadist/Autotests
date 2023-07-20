package tests;

import org.testng.annotations.BeforeClass;
import testDate.*;
import utils.GsonUtils;
import utils.Randomizer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class BaseTest {

    public final static Path USERS_DATE_PATH = Paths.get("src/test/resources/usersDate.json");
    public final static Path POSTS_DATE_PATH = Paths.get("src/test/resources/postsDate.json");

    protected static UsersDate usersDate;
    protected static PostsDate postsDate;

    @BeforeClass
    public void before() {
        usersDate = Objects.requireNonNull(GsonUtils.createModel(UsersDate.class, USERS_DATE_PATH));
        postsDate = Objects.requireNonNull(GsonUtils.createModel(PostsDate.class, POSTS_DATE_PATH));

        postsDate.setNewPostBody(Randomizer.createRandomString());
        postsDate.setNewPostTitle(Randomizer.createRandomString());
    }

}
