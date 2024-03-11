package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class PagePrincipale {

    public static void main(String[] args) {
        afficherGUI();
    }

    public static void afficherGUI() {
        // Création de la fenêtre principale avec un titre
        JFrame fenetrePrincipale = new JFrame("Financement Automobile");
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture de l'application à la fermeture
        fenetrePrincipale.setSize(600, 400); // Définition de la taille de la fenêtre

        // Création du panel avec une image de fond
        Panel panel = new Panel("photo/voiture1.jpg");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Ajout des composants  au panel
        panel.add(creerLabel("Financement Automobile XYZ", 24));
        panel.add(creerLabel("<html>Financement Automobile vous offre des solutions de financement simples et adaptées à vos besoins. Que ce soit pour une voiture neuve ou d'occasion, bénéficiez de nos taux avantageux et d'un service rapide. Faites confiance à nous pour financer facilement la voiture de vos choix.</html>", 16));
        panel.add(creerLabel("Contactez-nous : contact@financementXYZ.com", 14));
        panel.add(creerLabel("Téléphone : 123-456 789", 14));
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajoute un espace entre les composants

        // Création et ajout du bouton d'inscription
        JButton boutonInscription = creerBouton("Inscription");
        boutonInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormInscription().setVisible(true); // Affichage de la fenêtre d'inscription
            }
        });
        panel.add(boutonInscription);
        // Création et ajout du bouton connexion
        JButton boutonConnexion = creerBouton("Connexion");
        boutonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Affichage de la fenêtre de connexion
                new FormConnexion().setVisible(true);
            }
        });
        panel.add(boutonConnexion);
        JButton boutonStatusDemande = creerBouton("Statut demande de financement");
        boutonStatusDemande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher la vue FinancingStatus
                new FinancingStatus().setVisible(true);
            }
        });
        panel.add(boutonStatusDemande);
        // Ajout du panel à la fenêtre principale et affichage
        fenetrePrincipale.add(panel);
        fenetrePrincipale.setLocationRelativeTo(null); // Centrage de la fenêtre
        fenetrePrincipale.setVisible(true); // Rendre la fenêtre visible
    }

    //  créer un JLabel stylisé
    private static JLabel creerLabel (String texte, int taille) {
        JLabel label = new JLabel(texte);
        label.setFont(new Font("Serif", Font.BOLD, taille)); // Ajouter la police et de la taille
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage du texte
        return label;
    }

    // Méthode pour créer un JButton
    private static JButton creerBouton (String texte) {
        JButton bouton = new JButton(texte);
        bouton.setFont(new Font("Serif", Font.BOLD, 14)); // Ajouter
        // la police et de la taille
        bouton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage du bouton
        return bouton;
    }

    // Classe interne Panel avec image de fond
    static class Panel extends JPanel {
        private Image backgroundImage;

        public Panel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage(); // Chargement de l'image
        }

        // methode paintComponent
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this); // Dessin de l'image de fond
        }
    }
}
