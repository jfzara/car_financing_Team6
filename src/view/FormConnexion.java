package view;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import view.PasswordHashing;
public class FormConnexion extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public FormConnexion() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Connexion");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Se connecter");
        loginButton.addActionListener(this::login);
        panel.add(loginButton);

        add(panel);
        pack();
        setVisible(true);
    }

    private void login(ActionEvent e) {
        try (Connection conn = PostgresSQLConfig.connect()) {
            String query = "SELECT email, password_hash, salt FROM clients WHERE email = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, emailField.getText());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("password_hash");
                        byte[] salt = Base64.getDecoder().decode(rs.getString("salt"));
                        String inputHash = PasswordHashing.get_SHA_256_SecurePassword(new String(passwordField.getPassword()), salt);

                        if (storedHash.equals(inputHash)) {
                            JOptionPane.showMessageDialog(this, "Connexion réussie !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Utilisateur introuvable.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}

