package view;

import modele.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;

public class FormConnexion extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public FormConnexion() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        mainPanel.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        mainPanel.add(emailField);
        mainPanel.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(this::login);
        mainPanel.add(loginButton);

        add(mainPanel);
        setVisible(true);
    }

    private void login(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());


        System.out.println("Tentative de connexion pour l'email: " + email);

        try {
            Client client = PostgresSQLConfig.getClientByEmail(email);
            if (client != null) {

                System.out.println("Utilisateur trouvé dans la base de données.");

                byte[] salt = client.getSalt();
                String hashedInputPassword = PasswordHashing.get_SHA_256_SecurePassword(password, salt);


                System.out.println("Hash du mot de passe saisi: " + hashedInputPassword);
                System.out.println("Hash du mot de passe attendu: " + client.getPassword());

                if (client.getPassword().equals(hashedInputPassword)) {
                    JOptionPane.showMessageDialog(this, "Connexion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    System.out.println("Échec de la correspondance des mots de passe.");
                    JOptionPane.showMessageDialog(this, "E-mail ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                System.out.println("Aucun utilisateur trouvé pour cet e-mail.");
                JOptionPane.showMessageDialog(this, "E-mail ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {

            System.err.println("Erreur SQL: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("ErrorCode: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }



}

