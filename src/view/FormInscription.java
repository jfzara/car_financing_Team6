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
    private JTextField[] textFields;
    private JButton submitButton;

    public FormInscription() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Formulaire d'Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        userTypeComboBox = new JComboBox<>(new String[]{"", "Client", "Investisseur"});
        formPanel.add(new JLabel("Type d'utilisateur:"));
        formPanel.add(userTypeComboBox);

        String[] labels = {
                "Nom complet:", "Adresse électronique:", "Mot de passe:",
                "Score de crédit:", "Date de naissance:", "Statut marital:", "Années au Canada:",
                "Nom de la banque:", "Détails du compte bancaire:", "Niveau de risque:", "Niveau d'éducation:"
        };

        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
            if (i > 2) {
                textFields[i].setEnabled(false);
            }
        }

        submitButton = new JButton("S'inscrire");
        submitButton.addActionListener(this::submitForm);
        formPanel.add(submitButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);


        userTypeComboBox.addActionListener(e -> updateFormFieldsBasedOnUserType());
    }

    private void updateFormFieldsBasedOnUserType() {
        String selectedType = (String) userTypeComboBox.getSelectedItem();
        boolean isClient = "Client".equals(selectedType);
        boolean isInvestor = "Investisseur".equals(selectedType);


        for (JTextField textField : textFields) {
            textField.setText("");
        }


        for (int i = 3; i <= 6; i++) {
            textFields[i].setEnabled(isClient);
        }
        for (int i = 7; i < textFields.length; i++) {
            textFields[i].setEnabled(isInvestor);
        }
    }
    private void submitForm(ActionEvent e) {
        String selectedUserType = (String) userTypeComboBox.getSelectedItem();
        if ("Client".equals(selectedUserType)) {
            submitClientForm(e);
        } else if ("Investisseur".equals(selectedUserType)) {
            submitInvestorForm(e);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un type d'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitClientForm(ActionEvent e) {
        // Vérification basique de validité
        if (textFields[1].getText().isEmpty() || textFields[2].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Les champs Email et Mot de passe ne doivent pas être vides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Continuer avec la tentative d'inscription
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
                // Ajout de validations simples pour les champs numériques et la date
                try {
                    pstmt.setInt(5, Integer.parseInt(textFields[3].getText()));
                    pstmt.setDate(6, Date.valueOf(textFields[4].getText())); // Assurez-vous que la date est au format YYYY-MM-DD
                    pstmt.setString(7, textFields[5].getText());
                    pstmt.setInt(8, Integer.parseInt(textFields[6].getText()));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides pour le score de crédit et les années au Canada.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void submitInvestorForm(ActionEvent e) {
        // Basic validity check for common fields
        if (textFields[1].getText().isEmpty() || textFields[2].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Les champs Email et Mot de passe ne doivent pas être vides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = PostgresSQLConfig.connect()) {
            byte[] salt = PasswordHashing.getSalt();
            String passwordHash = PasswordHashing.get_SHA_256_SecurePassword(textFields[2].getText(), salt);
            String saltStr = Base64.getEncoder().encodeToString(salt);


            String query = "INSERT INTO investors (full_name, email, password_hash, salt, bank_name, bank_account_details, risk_level, education_level, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, textFields[0].getText());
                pstmt.setString(2, textFields[1].getText());
                pstmt.setString(3, passwordHash);
                pstmt.setString(4, saltStr);
                pstmt.setString(5, textFields[7].getText());
                pstmt.setString(6, textFields[8].getText());
                pstmt.setString(7, textFields[9].getText());
                pstmt.setString(8, textFields[10].getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Inscription de l'investisseur réussie !");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription de l'investisseur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}