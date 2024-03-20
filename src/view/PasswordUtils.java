package view;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    // Méthode pour générer un salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public static String hashPassword(String passwordToHash, String salt) {
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        return PasswordHashing.get_SHA_256_SecurePassword(passwordToHash, saltBytes);
    }
}
