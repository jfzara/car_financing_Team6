package view;
import javax.swing.*;
import java.awt.*;

public class FinanceStatus extends JFrame {
    private JLabel brandLabel;
    private JLabel modelLabel;
    private JLabel yearLabel;
    private JLabel mileageLabel;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton modifyButton;
    private JButton returnButton;

    public FinanceStatus() {
        // Initialisation de la fenêtre
        setTitle("Statut de la demande de financement");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement cette fenêtre
        setLayout(new GridLayout(7, 1));

        // Initialisation des composants
        brandLabel = new JLabel("Marque : ");
        modelLabel = new JLabel("Modèle : ");
        yearLabel = new JLabel("Année : ");
        mileageLabel = new JLabel("Kilométrage : ");
        statusLabel = new JLabel("Statut : En cours");
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(20); // Valeur de la barre de progression
        modifyButton = new JButton("Modifier la demande");
        modifyButton.setEnabled(false); // Le bouton est désactivé par défaut
        returnButton = new JButton("Retour");

        // Ajout des composants à la fenêtre
        add(brandLabel);
        add(modelLabel);
        add(yearLabel);
        add(mileageLabel);
        add(statusLabel);
        add(progressBar);
        add(modifyButton);
        add(returnButton);

        // Ajout des listeners aux boutons
        returnButton.addActionListener(e -> handleReturnButtonClick());

        // Affichage de la fenêtre
        setVisible(true);
    }

    // Méthode pour gérer le clic sur le bouton "Retour"
    private void handleReturnButtonClick() {
        // Fermer cette fenêtre
        dispose();
    }

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
}