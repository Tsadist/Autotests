package tests;

import com.mashape.unirest.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseHandler;
import models.Posts;
import models.Users;
import unirest.ApiUtils;
import utils.GsonUtils;

import static testDate.Endpoints.*;

@Slf4j
public class RestAPITest extends BaseTest {

    private static HttpResponse<?> response;
    private static Posts post;
    private static Posts[] postsArray;
    private static Users user;
    private static Users[] usersArray;

    @Test
    public void responseSortTest(){

        response = ApiUtils.get(GET_ALL_POSTS);
        postsArray = GsonUtils.createModel(Posts[].class, response);

        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 200");

        log.info("Шаг 2, проверка типа данных в теле ответа");
        Assert.assertTrue(ResponseHandler.isJsonInResponse(response),
                "Тип данных в теле ответа на шаге 2 отличается от json");

        log.info("Шаг 3, проверка сортировки постов");
        Assert.assertTrue(ResponseHandler.isMessagesSortedAscendingOrderId(postsArray),
                "Данные на шаге 3 отсортированы не по возрастанию");
    }

    @Test
    public void responseCorrectTest(){

        String idNinetyNine = "99";
        
        response = ApiUtils.get(String.format(GET_POSTS_BY_ID, idNinetyNine));
        post = GsonUtils.createModel(Posts.class, response);
        
        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 200");

        log.info("Шаг 2, проверка userId указанного поста");
        Assert.assertEquals(postsDate.getPostNinetyNineUserId(), post.getUserId(),
                "UserId в шаге 2 не соответствует тестовым данным");

        log.info("Шаг 3, проверка id указанного поста");
        Assert.assertEquals(postsDate.getPostNinetyNineId(), post.getId(),
                "Id в шаге 3 не соответствует тестовым данным");

        log.info("Шаг 4, проверка на пустоту title выбранного поста");
        Assert.assertNotNull(post.getTitle(),
                "Title поста в шаге 4 пустое");

        log.info("Шаг 5, проверка на пустоту body выбранного поста");
        Assert.assertNotNull(post.getBody(),
                "Body поста в шаге 5 пустое");
    }
    
    @Test
    public void uncorrectedResponseTest(){

        String idOneHundredFifty = "150";

        response = ApiUtils.get(String.format(GET_POSTS_BY_ID, idOneHundredFifty));
        post = GsonUtils.createModel(Posts.class, response);

        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 404");

        log.info("Шаг 2, проверка на пустоты тела ответа");
        Assert.assertTrue(ResponseHandler.isResponseBodyEmpty(response),
                "Тело ответа в шаге 2 не пустое");
    }

    @Test
    public void correctResponsePostRequestTest() {

        String postRequestJson = ResponseHandler
                .getStringWithNewPost(postsDate.getNewPostUserId(), postsDate.getNewPostTitle(), postsDate.getNewPostBody());

        response = ApiUtils.post(GET_ALL_POSTS, postRequestJson);

        Posts postsRequest = GsonUtils.createModel(Posts.class, postRequestJson);
        post = GsonUtils.createModel(Posts.class, response);

        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 201");

        log.info("Шаг 2, проверка тела ответа на соответствие телу запроса");
        Assert.assertEquals(post, postsRequest,
                "Тело ответа на шаге 2 не соответствует телу запроса");

    }

    @Test
    public void selectedUserMatchesGivenFileTest() {

        int arrayCorrection = -1;
        int numberArrayFifeUser = usersDate.getNumberFifeUser() + arrayCorrection;

        response = ApiUtils.get(GET_ALL_USERS);
        usersArray = GsonUtils.createModel(Users[].class, response);

        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 200");

        log.info("Шаг 2, проверка типа данных в теле ответа");
        Assert.assertTrue(ResponseHandler.isJsonInResponse(response),
                "Тип данных в теле ответа на шаге 2 отличается от json");

        log.info("Шаг 3, проверка тела заданного юзера на соответствие тестовым данным");
        Assert.assertEquals(usersDate.getUserFifeDate(), usersArray[numberArrayFifeUser],
                "Тела заданного юзера на шаге 3 не соответствует тестовым данным");
    }

    @Test
    public void specifiedUserMatchesGivenFileTest() {

        String idFife = usersDate.getNumberFifeUser().toString();

        response = ApiUtils.get(String.format(GET_USERS_BY_ID, idFife));
        user = GsonUtils.createModel(Users.class, response);

        log.info("Шаг 1, проверека статуса выполнения");
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatus(),
                "Статус выполненения запроса в шаге 1 отличается от 200");

        log.info("Шаг 2, проверка данных определнного юзера на соответствие тестовым данным");
        Assert.assertEquals(usersDate.getUserFifeDate(), user,
                "В шаге 2 данные в указанном юзере не соответствуют тестовым данным");
    }

}
