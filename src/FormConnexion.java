import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormConnexion extends JFrame {
    // Déclaration des composants du formulaire
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public FormConnexion() {
        setTitle("Connexion"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportement à la fermeture
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        // Utilisation de GridBagLayout pour controler les  placement des composants
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Ajoute des marges

        // Ajout des champs de texte et de leurs étiquettes
        panel.add(new JLabel("Adresse électronique:"), gbc);
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        panel.add(new JLabel("Mot de passe:"), gbc);
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Configuration et ajout du bouton de connexion
        loginButton = new JButton("Connexion");
        gbc.fill = GridBagConstraints.NONE;
        panel.add(loginButton, gbc);

        // Ajoute le panneau à la fenêtre
        add(panel);

        // Configuration de l'action à exécuter lors du clic sur le bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        pack(); // Ajuste la taille de la fenêtre en fonction des composants
    }

    private void login() {
        // Récupération du email et du mot de passe saisis par l'utilisateur
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Validation des entrées : vérifie si l'un des champs est vide
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (email.equals("test@example.com") && password.equals("password")) {

            JOptionPane.showMessageDialog(this, "Connexion réussie!");

        } else {

            JOptionPane.showMessageDialog(this, "Échec de la connexion. Veuillez vérifier vos identifiants.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FormConnexion().setVisible(true);
    }

}

