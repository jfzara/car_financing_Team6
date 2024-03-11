package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FinancingStatus extends JFrame {
    private JLabel brandLabel;
    private JLabel modelLabel;
    private JLabel yearLabel;
    private JLabel mileageLabel;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton modifyButton;
    private JButton returnButton;

    public FinancingStatus() {
        // Initialisation de la fenêtre
        setTitle("Statut de la demande de financement");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation des composants
        brandLabel = new JLabel("Marque : ");
        modelLabel = new JLabel("Modèle : ");
        yearLabel = new JLabel("Année : ");
        mileageLabel = new JLabel("Kilométrage : ");
        statusLabel = new JLabel("Statut : En cours");
        progressBar = new JProgressBar(0, 100);
        modifyButton = new JButton("Modifier la demande");
        returnButton = new JButton("Retour");

        // Ajout des composants à la fenêtre
        setLayout(new GridLayout(7, 1));
        add(brandLabel);
        add(modelLabel);
        add(yearLabel);
        add(mileageLabel);
        add(statusLabel);
        add(progressBar);
        add(modifyButton);
        add(returnButton);

        // Ajout des listeners aux boutons
        modifyButton.addActionListener(e -> handleModifyButtonClick());
        returnButton.addActionListener(e -> handleReturnButtonClick());

        // Affichage de la fenêtre
        setVisible(true);
    }

    // Méthode pour valider le champ VIN
    private boolean validateVIN(String vin) {
        return vin.length() == 17;
    }

    // Méthode pour valider le montant du prêt désiré
    private boolean validateLoanAmount(int amount) {
        return amount <= 60000;
    }

    // Méthode pour valider la durée du prêt
    private boolean validateLoanDuration(int duration) {
        return duration <= 4;
    }

    // Méthode pour valider le kilométrage
    private boolean validateMileage(int mileage, boolean isUsedCar) {
        return !isUsedCar || mileage <= 230000;
    }

    // Méthode pour gérer le clic sur le bouton "Modifier la demande"
    private void handleModifyButtonClick() {
        String vin = "";
        int loanAmount = 0;
        int loanDuration = 0;
        int mileage = 0;
        boolean isUsedCar = false;

        boolean isValidVIN = validateVIN(vin);
        boolean isValidLoanAmount = validateLoanAmount(loanAmount);
        boolean isValidLoanDuration = validateLoanDuration(loanDuration);
        boolean isValidMileage = validateMileage(mileage, isUsedCar);

        if (!isValidVIN) JOptionPane.showMessageDialog(this, "Le VIN doit contenir exactement 17 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
        if (!isValidLoanAmount) JOptionPane.showMessageDialog(this, "Le montant du prêt ne peut excéder 60 000 $.", "Erreur", JOptionPane.ERROR_MESSAGE);
        if (!isValidLoanDuration) JOptionPane.showMessageDialog(this, "La durée du prêt ne peut être supérieure à 4 ans.", "Erreur", JOptionPane.ERROR_MESSAGE);
        if (!isValidMileage) JOptionPane.showMessageDialog(this, "Le kilométrage pour un véhicule d'occasion ne doit pas dépasser 230 000.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    // Méthode pour gérer le clic sur le bouton "Retour"
    private void handleReturnButtonClick() {
        // Implémenter la logique pour retourner au menu principal
    }

    // Méthodes pour mettre à jour les informations affichées dans la vue

    // Méthode pour mettre à jour la marque du véhicule
    public void setBrand(String brand) {
        brandLabel.setText("Marque : " + brand);
    }

    // Méthode pour mettre à jour le modèle du véhicule
    public void setModel(String model) {
        modelLabel.setText("Modèle : " + model);
    }

    // Méthode pour mettre à jour l'année du véhicule
    public void setYear(int year) {
        yearLabel.setText("Année : " + year);
    }

    // Méthode pour mettre à jour le kilométrage du véhicule
    public void setMileage(int mileage) {
        mileageLabel.setText("Kilométrage : " + mileage);
    }

    // Méthode pour mettre à jour le statut de la demande de financement
    public void setStatus(String status) {
        statusLabel.setText("Statut : " + status);
    }

    // Méthode pour mettre à jour la barre de progression de la demande de financement
    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }

    // Méthodes pour activer ou désactiver le bouton de modification de la demande
    public void enableModifyButton() {
        modifyButton.setEnabled(true);
    }

    public void disableModifyButton() {
        modifyButton.setEnabled(false);
    }

    // Méthode pour ajouter un écouteur d'événements au bouton de retour
    public void addReturnButtonListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
}