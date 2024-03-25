package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import view.FormConnexion;

public class FormConnexionTest {

    @Test
    public void PasswordReturnsTrue() {
        FormConnexion formConnexion = new FormConnexion();
        assertTrue(formConnexion.checkPassword("password123", "password123"),
                "La méthode checkPassword devrait retourner true pour des mots de passe correspondants.");
    }

    @Test
    public void PasswordReturnsFalse() {
        FormConnexion formConnexion = new FormConnexion();
        assertFalse(formConnexion.checkPassword("password123", "wrongPassword"),
                "La méthode checkPassword devrait retourner false pour des mots de passe non correspondants.");
    }
}
