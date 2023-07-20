package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Randomizer {

    private final static Random RANDOM = new Random();
    private static final int MAX_LENGTH = 256;

    public static String getRandomString() {
        return RandomStringUtils.randomAscii(RANDOM.nextInt(MAX_LENGTH));
    }
}
