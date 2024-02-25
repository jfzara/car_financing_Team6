import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormInscription extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton submitButton;

    private JLabel creditScoreLabel;
    private JLabel birthDateLabel;
    private JLabel maritalStatusLabel;
    private JLabel yearsInCanadaLabel;

    private JLabel bankNameLabel;
    private JLabel bankAccountDetailsLabel;
    private JLabel investorRiskLevelLabel;
    private JLabel investorEducationLevelLabel;

    private JLabel userDetailsLabel;

    public FormInscription() {
        setTitle("Formulaire d'Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        userTypeComboBox = new JComboBox<>(new String[]{"", "Client", "Investisseur"}); // Option vide ajoutée
        formPanel.add(new JLabel("Type d'utilisateur:"));
        formPanel.add(userTypeComboBox);

        labels = new JLabel[]{
                new JLabel("Nom complet:"),
                new JLabel("Adresse électronique:"),
                new JLabel("Mot de passe:")
        };
        textFields = new JTextField[11];
        for (int i = 0; i < labels.length; i++) {
            formPanel.add(labels[i]);
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
        }

        // Champs spécifiques pour Client
        creditScoreLabel = new JLabel("Score de crédit:");
        birthDateLabel = new JLabel("Date de naissance:");
        maritalStatusLabel = new JLabel("Statut marital:");
        yearsInCanadaLabel = new JLabel("Années au Canada:");

        // Champs spécifiques pour Investisseur
        bankNameLabel = new JLabel("Nom de la banque:");
        bankAccountDetailsLabel = new JLabel("Détails du compte bancaire:");
        investorRiskLevelLabel = new JLabel("Niveau de risque:");
        investorEducationLevelLabel = new JLabel("Niveau d'éducation:");

        // Ajout des champs spécifiques pour Client et Investisseur avec la désactivation initiale
        formPanel.add(creditScoreLabel);
        textFields[3] = new JTextField();
        formPanel.add(textFields[3]);
        textFields[3].setEnabled(false); // Désactivé initialement

        formPanel.add(birthDateLabel);
        textFields[4] = new JTextField();
        formPanel.add(textFields[4]);
        textFields[4].setEnabled(false); // Désactivé initialement

        formPanel.add(maritalStatusLabel);
        textFields[5] = new JTextField();
        formPanel.add(textFields[5]);
        textFields[5].setEnabled(false); // Désactivé initialement

        formPanel.add(yearsInCanadaLabel);
        textFields[6] = new JTextField();
        formPanel.add(textFields[6]);
        textFields[6].setEnabled(false); // Désactivé initialement

        formPanel.add(bankNameLabel);
        textFields[7] = new JTextField();
        formPanel.add(textFields[7]);
        textFields[7].setEnabled(false); // Désactivé initialement

        formPanel.add(bankAccountDetailsLabel);
        textFields[8] = new JTextField();
        formPanel.add(textFields[8]);
        textFields[8].setEnabled(false); // Désactivé initialement

        formPanel.add(investorRiskLevelLabel);
        textFields[9] = new JTextField();
        formPanel.add(textFields[9]);
        textFields[9].setEnabled(false); // Désactivé initialement

        formPanel.add(investorEducationLevelLabel);
        textFields[10] = new JTextField();
        formPanel.add(textFields[10]);
        textFields[10].setEnabled(false); // Désactivé initialement

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        userDetailsLabel = new JLabel();
        mainPanel.add(userDetailsLabel, BorderLayout.NORTH);

        submitButton = new JButton("S'inscrire");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajoutez votre logique de bouton ici
            }
        });
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        setVisible(true);

        // Ajout d'un écouteur d'événements pour le changement de sélection dans la liste déroulante
        userTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFormFields();
            }
        });
    }

    // Méthode pour désactiver les champs inutiles en fonction du type d'utilisateur sélectionné
    private void updateFormFields() {
        String selectedUserType = (String) userTypeComboBox.getSelectedItem();
        boolean isClient = selectedUserType.equals("Client");

        // Champs spécifiques pour Client
        creditScoreLabel.setEnabled(isClient);
        textFields[3].setEnabled(isClient);
        birthDateLabel.setEnabled(isClient);
        textFields[4].setEnabled(isClient);
        maritalStatusLabel.setEnabled(isClient);
        textFields[5].setEnabled(isClient);
        yearsInCanadaLabel.setEnabled(isClient);
        textFields[6].setEnabled(isClient);

        // Champs spécifiques pour Investisseur
        bankNameLabel.setEnabled(!isClient);
        textFields[7].setEnabled(!isClient);
        bankAccountDetailsLabel.setEnabled(!isClient);
        textFields[8].setEnabled(!isClient);
        investorRiskLevelLabel.setEnabled(!isClient);
        textFields[9].setEnabled(!isClient);
        investorEducationLevelLabel.setEnabled(!isClient);
        textFields[10].setEnabled(!isClient);
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[^a-zA-Z0-9\\s].*");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormInscription();
            }
        });
    }
}