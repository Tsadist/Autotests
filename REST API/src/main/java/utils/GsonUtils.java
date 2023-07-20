package utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class GsonUtils {

    private GsonUtils() {
    }

    private static final Gson GSON = new Gson();

    public static <T> T createModel(Class<? extends T> clazz, Path filePath) {
        try {
            String resource = Files.readString(filePath);
            return GSON.fromJson(resource, clazz);

        } catch (IOException e) {
            log.error("Ошибка при создании модели из Json файла" + e.getMessage());
            return null;
        }
    }

    public static <T> T createModel(Class<? extends T> clazz, HttpResponse<?> response) {
        return GSON.fromJson(response.getBody().toString(), clazz);
    }

    public static <T> T createModel(Class<? extends T> clazz, String json) {
        return GSON.fromJson(json, clazz);
    }

    public static String createJson(Object object){
        return GSON.toJson(object);
    }
}
