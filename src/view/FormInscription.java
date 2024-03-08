package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.nio.charset.StandardCharsets;
import view.PasswordHashing;

public class FormInscription extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTextField[] textFields = new JTextField[7];
    private JLabel creditScoreLabel, birthDateLabel, maritalStatusLabel, yearsInCanadaLabel;
    private JLabel bankNameLabel, bankAccountDetailsLabel, investorRiskLevelLabel, investorEducationLevelLabel;

    public FormInscription() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Formulaire d'Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        mainPanel.add(formPanel, BorderLayout.CENTER);


        initializeFormElements(formPanel);

        JButton submitButton = new JButton("S'inscrire");
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> submitForm());

        add(mainPanel);
        setVisible(true);
    }

    private void initializeFormElements(JPanel formPanel) {
        userTypeComboBox = new JComboBox<>(new String[]{"", "Client", "Investisseur"});
        formPanel.add(new JLabel("Type d'utilisateur:"));
        formPanel.add(userTypeComboBox);

        String[] labelsText = {"Nom complet:", "Adresse électronique:", "Mot de passe:", "Score de crédit:", "Date de naissance:", "Statut marital:", "Années au Canada:"};
        for (int i = 0; i < labelsText.length; i++) {
            formPanel.add(new JLabel(labelsText[i]));
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
        }

        userTypeComboBox.addActionListener(e -> updateFormFields());
        updateFormFields();
    }

    private void submitForm() {
        String fullName = textFields[0].getText();
        String email = textFields[1].getText();
        String password = textFields[2].getText();
        String userType = (String) userTypeComboBox.getSelectedItem();

        try (Connection conn = PostgresSQLConfig.connect()) {
            if ("Client".equals(userType)) {
                handleClientRegistration(conn, fullName, email, password);
            } else if ("Investisseur".equals(userType)) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleClientRegistration(Connection conn, String fullName, String email, String password) {
        try {
            byte[] salt = PasswordHashing.getSalt();
            String passwordHash = PasswordHashing.get_SHA_256_SecurePassword(password, salt);
            int creditScore = Integer.parseInt(textFields[3].getText());
            Date birthDate = Date.valueOf(textFields[4].getText());
            String maritalStatus = textFields[5].getText();
            int yearsInCanada = Integer.parseInt(textFields[6].getText());


            String insertQuery = "INSERT INTO clients (full_name, email, password_hash, salt, credit_score, birth_date, marital_status, years_in_canada, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

            try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {
                // Set parameters for the prepared statement
                statement.setString(1, fullName);
                statement.setString(2, email);
                statement.setString(3, passwordHash);
                statement.setBytes(4, salt);
                statement.setInt(5, creditScore);
                statement.setDate(6, birthDate);
                statement.setString(7, maritalStatus);
                statement.setInt(8, yearsInCanada);


                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Inscription réussie !");
            }
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                JOptionPane.showMessageDialog(null, "Cette adresse email est déjà utilisée.", "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            ex.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Le score de crédit et les années au Canada doivent être des nombres.", "Erreur de Format", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la génération du hash du mot de passe.", "Erreur Interne", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }




    private void updateFormFields() {
        boolean isClient = "Client".equals(userTypeComboBox.getSelectedItem());
        for (int i = 3; i < textFields.length; i++) {
            textFields[i].setEnabled(isClient);
        }
    }

    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormInscription::new);
    }
}
