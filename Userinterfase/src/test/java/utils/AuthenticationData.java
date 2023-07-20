package utils;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationData {

    private final Randomizer random = new Randomizer();
    private final Map<String, String> dateAuthorization = new HashMap<>();

    private final StringBuilder password = new StringBuilder();
    private final StringBuilder email = new StringBuilder();
    private final StringBuilder domain = new StringBuilder();

    private final int countCharactersPassword;
    private final int countCharactersEmail;
    private final int countCharactersDomain;

    private char charEmail;

    public AuthenticationData(int countCharacterPassword, int countCharacterEmail, int countCharacterDomain) {
        this.countCharactersPassword = countCharacterPassword;
        this.countCharactersEmail = countCharacterEmail;
        this.countCharactersDomain = countCharacterDomain;
    }

    public Map<String, String> createdPasswordEmailDomain() {
        createdEmail();
        createdPassword();
        createdDomain();

        dateAuthorization.put("password", password.toString());
        dateAuthorization.put("email", email.toString());
        dateAuthorization.put("domain", domain.toString());

        return dateAuthorization;
    }

    private void createdPassword() {
        random.createRandomString(countCharactersPassword, password);
        random.appendCharacterLowerCase(password);
        random.appendCharacterUpperCase(password);
        random.appendCharacterNumber(password);
        password.append(charEmail);
    }


    private void createdEmail() {
        random.createRandomString(countCharactersEmail, email);
        charEmail = email.charAt((int) (Math.random() * email.length()));
    }

    private void createdDomain() {
        random.createRandomString(countCharactersDomain, domain);
    }
}
