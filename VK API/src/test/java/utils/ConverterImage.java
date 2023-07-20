package utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ConverterImage {

    private final static int MIN_SIZE_BYTE_ARRAY = 0;
    private final static String FORMAT_NAME = "jpg";

    public static byte [] getByteArrayFromImage(String fileName){
        try {
            BufferedImage bImage = ImageIO.read(new File(fileName));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, FORMAT_NAME, bos );
            return bos.toByteArray();
        } catch (IOException e) {
            log.error("Проблема при преобразовании изображения к массиву байт " + e.getMessage());
            return new byte[MIN_SIZE_BYTE_ARRAY];
        }
    }
}
