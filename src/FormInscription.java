import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormInscription extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton submitButton;

    public FormInscription() {
        setTitle("Formulaire d'Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        userTypeComboBox = new JComboBox<>(new String[]{"Client", "Investisseur"});
        formPanel.add(new JLabel("Type d'utilisateur:"));
        formPanel.add(userTypeComboBox);

        labels = new JLabel[]{
                new JLabel("Nom complet:"),
                new JLabel("Adresse électronique:"),
                new JLabel("Mot de passe:")
        };
        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            formPanel.add(labels[i]);
            textFields[i] = new JTextField();
            formPanel.add(textFields[i]);
        }

        mainPanel.add(formPanel, BorderLayout.CENTER);

        submitButton = new JButton("S'inscrire");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (userTypeComboBox.getSelectedItem().equals("Client")) {
                    // Traiter les données spécifiques aux clients
                } else if (userTypeComboBox.getSelectedItem().equals("Investisseur")) {
                    // Traiter les données spécifiques aux investisseurs
                }
            }
        });
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormInscription();
            }
        });
    }
}