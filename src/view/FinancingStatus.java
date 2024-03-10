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

        // Affichage de la fenêtre
        setVisible(true);
    }

    // Méthodes pour mettre à jour les informations affichées dans la vue
    public void setBrand(String brand) {
        brandLabel.setText("Marque : " + brand);
    }

    // Méthode pour mettre à jour le modèle
    public void setModel(String model) {
        modelLabel.setText("Modèle : " + model);
    }

    // Méthode pour mettre à jour l'année
    public void setYear(int year) {
        yearLabel.setText("Année : " + year);
    }

    // Méthode pour mettre à jour le kilométrage
    public void setMileage(int mileage) {
        mileageLabel.setText("Kilométrage : " + mileage);
    }

    // Méthode pour mettre à jour le statut
    public void setStatus(String status) {
        statusLabel.setText("Statut : " + status);
    }

    // Méthode pour mettre à jour la barre de progression
    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }

    // Méthodes pour activer/désactiver les boutons
    public void enableModifyButton() {
        modifyButton.setEnabled(true);
    }

    public void disableModifyButton() {
        modifyButton.setEnabled(false);
    }

    // Méthode pour ajouter un gestionnaire d'événements au bouton de retour
    public void addReturnButtonListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
}

