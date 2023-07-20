package unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import models.SettingFile;
import utils.GsonUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ApiUtils {

    private final static Path CONFIG_PATH = Paths.get("config.json");
    private final static String DEFAULT_URL = GsonUtils.createModel(SettingFile.class, CONFIG_PATH).getDefaultUrl();

    public static HttpResponse<?> get(String urlRequest) {
        try {
            return Unirest.get(DEFAULT_URL + "{request}")
                    .routeParam("request", urlRequest).asJson();
        } catch (UnirestException e) {
            log.error("Ошибка при отправке GET запроса" + e.getMessage());
            return null;
        }
    }

    public static HttpResponse<?> post(String urlRequest, String body) {
        try {

            return Unirest.post(DEFAULT_URL + "{request}")
                    .routeParam("request", urlRequest)
                    .header("Content-Type", "application/json")
                    .body(body)
                    .asJson();
        } catch (UnirestException e) {
            log.error("Ошибка при отправке POST запроса" + e.getMessage());
            return null;
        }
    }
}
