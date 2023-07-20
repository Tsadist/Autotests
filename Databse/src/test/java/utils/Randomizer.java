package utils;

import java.security.SecureRandom;

public class Randomizer {

    private final static SecureRandom RANDOM = new SecureRandom();
    private final static String NUMBER = "0123456789";

    public static char getRandomNumber() {
        return NUMBER.charAt(RANDOM.nextInt(NUMBER.length()));
    }
}
