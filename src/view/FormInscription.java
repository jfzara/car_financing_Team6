package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.sql.SQLException;
import view.PasswordHashing;
public class FormInscription extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTextField[] textFields = new JTextField[7]; // Correspondant aux labels
    private JButton submitButton;

    public FormInscription() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Formulaire d'Inscription");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2, 5, 5)); // 9 rows, 2 cols

        String[] labels = {"Nom complet", "Email", "Mot de passe", "Score de crédit", "Date de naissance", "Statut marital", "Années au Canada"};
        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
        }

        userTypeComboBox = new JComboBox<>(new String[]{"Client", "Investisseur"});
        formPanel.add(new JLabel("Type d'utilisateur:"));
        formPanel.add(userTypeComboBox);

        submitButton = new JButton("S'inscrire");
        submitButton.addActionListener(this::submitForm);
        formPanel.add(submitButton);

        add(formPanel);
        pack();
        setVisible(true);
    }

    private void submitForm(ActionEvent e) {
        try (Connection conn = PostgresSQLConfig.connect()) {
            byte[] salt = PasswordHashing.getSalt();
            String passwordHash = PasswordHashing.get_SHA_256_SecurePassword(textFields[2].getText(), salt);
            String saltStr = Base64.getEncoder().encodeToString(salt);

            String query = "INSERT INTO clients (full_name, email, password_hash, salt, credit_score, birth_date, marital_status, years_in_canada, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, textFields[0].getText());
                pstmt.setString(2, textFields[1].getText());
                pstmt.setString(3, passwordHash);
                pstmt.setString(4, saltStr);

                pstmt.setInt(5, Integer.parseInt(textFields[3].getText()));
                pstmt.setDate(6, Date.valueOf(textFields[4].getText()));
                pstmt.setString(7, textFields[5].getText()); // Statut marital
                pstmt.setInt(8, Integer.parseInt(textFields[6].getText()));


                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}
