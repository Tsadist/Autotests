package utils;

import java.security.SecureRandom;

public class Randomizer {

    private static final SecureRandom random = new SecureRandom();
    private static final String LETTRES = "qwertyuiopasdfghjklzxcvbnm";
    private static final int maxLength = 50;

    public static String createRandomString() {
        return createString(random.nextInt(maxLength));
    }

    private static String createString(int length) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            string.append(randomCharLetters());
        }
        return string.toString();
    }

    private static char randomCharLetters() {
        int randomIndex = random.nextInt(LETTRES.length());
        return LETTRES.charAt(randomIndex);
    }
}
