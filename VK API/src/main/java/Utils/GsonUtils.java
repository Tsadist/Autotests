package utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
public class GsonUtils {

    private static final Gson GSON = new Gson();

    public static <T> Optional<T> createModel(Class<? extends T> clazz, Path filePath) {
        try {
            String resource = Files.readString(filePath);
            return Optional.of(GSON.fromJson(resource, clazz));

        } catch (IOException e) {
            log.error("Ошибка при создании модели из Json файла " + e.getMessage());
            return Optional.empty();
        }
    }
}
