package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import models.Posts;

public class ResponseHandler {

    private final static String HEADER_CONTENT_TYPE = "Content-Type";
    private final static String CHECKABLE_DATA_TYPE = "json";
    private final static int COUNT_ELEMENTS_IN_EMPTY_JSON_OBJECT = 0;

    private ResponseHandler() {
    }

    public static boolean isMessagesSortedAscendingOrderId(Posts[] responseArray) {
        for (int i = 1; i < responseArray.length-1; i++) {
            int objectEarly = responseArray[i].getId();
            int objectNext = responseArray[i+1].getId();

            if(objectNext <= objectEarly) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJsonInResponse(HttpResponse<?> response) {
        return response.getHeaders().get(HEADER_CONTENT_TYPE).toString().contains(CHECKABLE_DATA_TYPE);
    }

    public static boolean isResponseBodyEmpty(HttpResponse<?> response) {
       return getJsonObject(response).size() <= COUNT_ELEMENTS_IN_EMPTY_JSON_OBJECT;
    }

    public static String getStringWithNewPost(int userId, String title, String body) {
        Posts postsRequest = new Posts();

        postsRequest.setBody(body);
        postsRequest.setTitle(title);
        postsRequest.setUserId(userId);

        return GsonUtils.createJson(postsRequest);
    }

    private static JsonObject getJsonObject(HttpResponse<?> response) {
        return new JsonParser().parse(response.getBody().toString()).getAsJsonObject();
    }
}
