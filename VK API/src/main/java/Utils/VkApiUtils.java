package utils;

import io.restassured.RestAssured;
import model.ConfigModel;
import model.LikesEndpoint;
import model.PhotosEndpoint;
import model.WallEndpoint;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class VkApiUtils {

    private final static Path CONFIG_FILE_PATH = Paths.get("APIconfig.json");
    private final static ConfigModel configModel = GsonUtils.createModel(ConfigModel.class, CONFIG_FILE_PATH).get();

    private final static String ownerIdParam = "owner_id";
    private final static String messageParam = "message";
    private final static String versionParam = "v";
    private final static String postIdParam = "post_id";
    private final static String attachmentsParam = "attachments";
    private final static String photoParam = "photo";
    private final static String serverParam = "server";
    private final static String hashParam = "hash";
    private final static String typeParam = "type";
    private final static String itemIdParam = "item_id";

    private final static String authorizationHeader = "Authorization";
    private final static String bearerHeader = "Bearer ";

    private final static String contentTypeFormDate = "multipart/form-data";
    private final static String multipartFormPhoto = "photo";

    private final static String response = "response";
    private final static Integer photosArrayFirstElementResponse = 0;
    private final static String commentIdResponse = "comment_id";
    private final static String itemResponse = "items";
    private final static String uploadUrlResponse = "upload_url";
    private final static String idResponse = "id";

    private final static String photoIdFormat = "photo%s_%s";


    public static Long wallPost(String ownerId, String message) {

        return Long.parseLong(RestAssured
                .given()
                .queryParam(ownerIdParam, ownerId)
                .queryParam(messageParam, message)
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .post(configModel.getDefaultUrlAPI() + WallEndpoint.post)
                .jsonPath()
                .getMap(response)
                .get(postIdParam)
                .toString());
    }

    public static int wallDelete(String ownerId, Long postId) {

        return RestAssured
                .given()
                .queryParam(ownerIdParam, ownerId)
                .queryParam(postIdParam, postId)
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .post(configModel.getDefaultUrlAPI() + WallEndpoint.delete)
                .jsonPath()
                .getInt(response);
    }

    public static Long wallEdit(String ownerId, Long postId, String message, String attachment) {

        return Long.parseLong(RestAssured
                .given()
                        .queryParam(ownerIdParam, ownerId)
                        .queryParam(postIdParam, postId)
                        .queryParam(messageParam, message)
                        .queryParam(attachmentsParam, attachment)
                        .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .post(configModel.getDefaultUrlAPI() + WallEndpoint.edit)
                .jsonPath()
                .getMap(response)
                .get(postIdParam)
                .toString());
    }

    public static String saveWallPhoto(Map<Object, Object> photoDate) {
        Map<Object, Object> map = (Map<Object, Object>) RestAssured
                .given()
                .queryParam(photoParam, photoDate.get(photoParam))
                .queryParam(serverParam, photoDate.get(serverParam))
                .queryParam(hashParam, photoDate.get(hashParam))
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .post(configModel.getDefaultUrlAPI() + PhotosEndpoint.saveWallPhoto)
                .jsonPath()
                .getList(response)
                .get(photosArrayFirstElementResponse);

        return String.format(photoIdFormat, map.get(ownerIdParam), map.get(idResponse));

    }

    public static Long createComment(String ownerId, Long postId, String message) {
        return Long.parseLong(RestAssured
                .given()
                .queryParam(ownerIdParam, ownerId)
                .queryParam(postIdParam, postId)
                .queryParam(messageParam, message)
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .post(configModel.getDefaultUrlAPI() + WallEndpoint.createComment)
                .jsonPath()
                .getMap(response)
                .get(commentIdResponse)
                .toString());
    }

    public static List<Long> getListLike (String ownerId, String type, Long itemId) {
        return  (List<Long>) RestAssured
                .given()
                .queryParam(typeParam, type)
                .queryParam(ownerIdParam, ownerId)
                .queryParam(itemIdParam, itemId)
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .get(configModel.getDefaultUrlAPI() + LikesEndpoint.getList)
                .jsonPath()
                .getMap(response)
                .get(itemResponse);
    }

    public static Map<Object, Object> transferPhotoServer(String filePath, String wallUploadServer) throws URISyntaxException {
        return RestAssured
                .given()
                .contentType(contentTypeFormDate)
                .multiPart(multipartFormPhoto, new File(Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResource(filePath)
                        .toURI()))
                .post(wallUploadServer)
                .jsonPath()
                .get();
    }

    public static String getWallUploadServer() {
        return RestAssured
                .given()
                .queryParam(versionParam, configModel.getVersion())
                .header(authorizationHeader, bearerHeader + configModel.getAccessToken())
                .get(configModel.getDefaultUrlAPI() + PhotosEndpoint.getWallUploadServer)
                .jsonPath()
                .getMap(response)
                .get(uploadUrlResponse)
                .toString();
    }

}
