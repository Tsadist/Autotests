package utils;

import java.security.SecureRandom;

public class Randomizer {

    private final SecureRandom random = new SecureRandom();

    private final String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
    private final String upperCase = lowerCase.toUpperCase();
    private final String number = "0123456789";
    private final String commonScroll = lowerCase + upperCase + number;


    public void createRandomString(int countCharacters, StringBuilder string) {
        for (int i = 0; i < countCharacters; i++) {
            int randomIndex = random.nextInt(commonScroll.length());
            string.append(commonScroll.charAt(randomIndex));
        }
    }

    public void appendCharacterLowerCase(StringBuilder stringToChange) {
        stringToChange.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
    }

    public void appendCharacterUpperCase(StringBuilder stringToChange) {
        stringToChange.append(upperCase.charAt(random.nextInt(upperCase.length())));
    }
    public void appendCharacterNumber(StringBuilder stringToChange) {
        stringToChange.append(number.charAt(random.nextInt(number.length())));
    }

}
