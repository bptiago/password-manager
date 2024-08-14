import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    private final String lowercase = "abcdefghijklmnoprstuvwxyz";
    private final String uppercase = lowercase.toUpperCase();
    private final String specialChars = "!@#$*()-_=+?^";
    private final String numbers = "0123456789";

    private final String characters;
    private final SecureRandom random;

    public Generator() {
        characters = uppercase + lowercase + specialChars + numbers;
        random = new SecureRandom();
    }

    // Gera senha com pelo menos: 1 símbolo, 1 letra maiúscula, 1 letra minúscula e 1 número
    public String generatePassword(int size) {
        StringBuilder password = new StringBuilder();

        // Pelo menos um caracter de cada tipo
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));

        // Preencher demais caracteres
        for (int i = 0; i < size - 4; i++) {
            int j = random.nextInt(characters.length());
            char character = characters.charAt(j);
            password.append(character);
        }

        // Misturar caracteres
        password = shufflePassword(password);

        return password.toString();
    }

    private StringBuilder shufflePassword(StringBuilder input) {
        char[] x = input.toString().toCharArray();

        List<Character> chars = new ArrayList<>();
        for (char c : x) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder shuffledString = new StringBuilder();
        for (char c : chars) shuffledString.append(c);
        return shuffledString;
    }

    public String getCharacters() {
        return characters;
    }
 }
