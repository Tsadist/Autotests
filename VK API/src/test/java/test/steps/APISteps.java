package test.steps;

import lombok.extern.slf4j.Slf4j;
import utils.VkApiUtils;

import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
public class APISteps {

    public static String getPhotoIdFromPost(String photoPath) {

        String wallUploadServer = VkApiUtils.getWallUploadServer();
        Map<Object, Object> photoDate = null;
        try {
            photoDate = VkApiUtils.transferPhotoServer(photoPath, wallUploadServer);
        } catch (URISyntaxException e) {
            log.error("Проблема при загрузке фото на сервер ВК" + e.getMessage());
        }
        return VkApiUtils.saveWallPhoto(photoDate);
    }
}
