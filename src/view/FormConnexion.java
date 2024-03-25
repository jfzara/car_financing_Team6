package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import view.InvestmentPage;
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

        if (!attemptLogin("clients", emailField.getText(), new String(passwordField.getPassword()))) {

            if (!attemptLogin("investors", emailField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Utilisateur introuvable ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean attemptLogin(String tableName, String email, String password) {

        String query = String.format("SELECT id, email, password_hash, salt FROM %s WHERE email = ?", tableName);
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    byte[] salt = Base64.getDecoder().decode(rs.getString("salt"));
                    String inputHash = PasswordHashing.get_SHA_256_SecurePassword(password, salt);

                    if (storedHash.equals(inputHash)) {

                        int userId = rs.getInt("id");


                        if (tableName.equals("investors")) {
                            SwingUtilities.invokeLater(() -> {
                                this.setVisible(false);
                                this.dispose();

                                new InvestmentPage(userId).setVisible(true);
                            });
                        } else {

                            JOptionPane.showMessageDialog(this, "Connexion réussie en tant que client!");
                        }
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public boolean checkPassword(String password, String storedPasswordHash) {
        // Implémentation de la logique de vérification du mot de passe
        return password.equals(storedPasswordHash);
    }
}