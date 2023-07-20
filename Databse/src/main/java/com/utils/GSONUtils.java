package com.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.HashMap;

@Slf4j
public class GSONUtils {

    private static final Gson GSON = new GsonBuilder().setDateFormat(DateFormat.LONG).create();

    public static <T> T getModelFromPath(Class<? extends T> clazz, Path filePath) {
        try {
            String resource = Files.readString(filePath);
            return GSON.fromJson(resource, clazz);
        } catch (IOException e) {
            log.error("Проблема при преобразовании json-файла к модели" + e.getMessage());
            return null;
        }
    }

    public static <T> T getModelFromHashMap(Class<? extends T> clazz, HashMap<?, ?> hashMap) {
        String jsonString = GSON.toJson(hashMap);
        return GSON.fromJson(jsonString, clazz);
    }

    public static <T> T getModelFromString(Class<? extends T> clazz, String date){
        return GSON.fromJson(date, clazz);
    }
}
