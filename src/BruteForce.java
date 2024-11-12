import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class BruteForce {
    private final String lowercase = "abcdefghijklmnopqrstuvwxyz";
    private final String uppercase = lowercase.toUpperCase();
    private final String specialChars = "!@#$*()-_=+?^";
    private final String numbers = "0123456789";

    private final String CHARS = lowercase + uppercase + specialChars + numbers;

    public void forceHash(String hash) {
        long initTime = System.currentTimeMillis();
        long limit = 5L;

        // Limitar em {limit} número de chars por senha
        for (int size = 1; size < limit; size++) {
            if (findPasswordsBySize(size, hash)) {
                break;
            }

            if (size == 4) {
                String str = String.format("Algoritmo falhou. É possível que a senha não tenha sido encontrada ou que ela seja maior que %d letras.", size);
                System.out.println(str);
            }
        }

        double timeElapsed = (double) (System.currentTimeMillis() - initTime) / 1000;
        String str = String.format("O algoritmo executou por %.2f segundos.", timeElapsed);
        System.out.println(str);
    }

    private boolean findPasswordsBySize(int size, String hash) {
        char [] chars = CHARS.toCharArray();
        int n = chars.length;
        return _findPasswords(chars, "", n, size, hash);
    }

    // Função recursiva
    private boolean _findPasswords(char[] chars, String prefix, int n, int k, String hash) {
        if (k == 0) {
            System.out.println(prefix);
            return false;
        }

        for (int i = 0; i < n; ++i) {
            String combination = prefix + chars[i];
            try {
                String tryHash = generateHash(combination);
                if (tryHash.equalsIgnoreCase(hash)) {
                    String str = String.format("Hash quebrado. A senha descoberta foi: %s.", combination);
                    System.out.println(str);
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            boolean found = _findPasswords(chars, combination, n, k - 1, hash);
            if (found) return true;
        }

        return false;
    }

    private String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

}
