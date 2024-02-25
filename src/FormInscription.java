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
                String password = textFields[2].getText();

                if (!validatePassword(password)) {
                    JOptionPane.showMessageDialog(FormInscription.this, "Le mot de passe" +
                            " doit contenir au moins 8 caractères, " +
                            "incluant au moins un chiffre et un caractère spécial.");
                    return;
                }

                String fullName = textFields[0].getText();
                String email = textFields[1].getText();
                String userType = (String) userTypeComboBox.getSelectedItem();

                if (userType.equals("Client")) {
                    String creditScoreText = textFields[3].getText();
                    try {
                        int creditScore = Integer.parseInt(creditScoreText);
                        String birthDate = textFields[4].getText();
                        String maritalStatus = textFields[5].getText();
                        String yearsInCanada = textFields[6].getText();

                        // Créer un objet Client
                        Client client = new Client(fullName, email, password, "", "", "",
                                creditScore, birthDate, maritalStatus, yearsInCanada);
                        // Afficher les informations du client dans la console
                        System.out.println(client.toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(FormInscription.this, "Veuillez saisir un nombre valide pour le score de crédit.");
                        return;
                    }
                } else if (userType.equals("Investisseur")) {
                    String bankName = textFields[7].getText();
                    String bankAccountDetails = textFields[8].getText();
                    String investorRiskLevel = textFields[9].getText();
                    String investorEducationLevel = textFields[10].getText();

                    // Créer un objet Investisseur
                    Investisseur investisseur = new Investisseur(fullName, email, password, "", bankName, bankAccountDetails,
                            investorRiskLevel, investorEducationLevel);
                    // Afficher les informations de l'investisseur dans la console
                    System.out.println(investisseur.toString());
                }

                JOptionPane.showMessageDialog(FormInscription.this, "Inscription réussie !");
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