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

        try {
            Client client = PostgresSQLConfig.getClientByEmail(email);
            if (client != null) {
                String hashedInputPassword = PasswordHashing.get_SHA_256_SecurePassword(password, client.getSalt());

                if (client.getPassword().equals(hashedInputPassword)) {
                    JOptionPane.showMessageDialog(this, "Connexion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "E-mail ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "E-mail ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


}

