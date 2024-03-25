package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JTextField;
import view.FormInscription;

public class FormInscriptionTest {

    @Test
    public void EmailAndPasswordAreNotEmpty() {
        FormInscription form = new FormInscription();
        JTextField[] fields = new JTextField[]{
                new JTextField("Nom"),
                new JTextField("Date de naissance")
        };

        assertTrue(form.validateInputFields("user@example.com", "password123", fields),
                "La validation devrait réussir lorsque l'email et le mot de passe ne sont pas vides");
    }

    @Test
    public void PasswordIsEmpty() {
        FormInscription form = new FormInscription();
        JTextField[] fields = new JTextField[]{
                new JTextField("Nom"),
                new JTextField("Date de naissance")
        };

        assertFalse(form.validateInputFields("", "password123", fields),
                "La validation devrait échouer lorsque l'email est vide.");

        assertFalse(form.validateInputFields("user@example.com", "", fields),
                "La validation devrait échouer lorsque le mot de passe est vide.");
    }

    @Test
    public void FieldsAreEmpty() {
        FormInscription form = new FormInscription();
        JTextField[] fields = new JTextField[]{
                new JTextField(""),
                new JTextField("Date de naissance")
        };

        assertFalse(form.validateInputFields("user@example.com", "password123", fields),
                "La validation échouer lorsque les champs ne sont pas tous remplis.");
    }
}

