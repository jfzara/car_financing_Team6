package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import view.PostgresSQLConfig;
public class InvestmentPage extends JFrame {

    private JLabel totalInvestedLabel;
    private JLabel currentBalanceLabel;
    private JTextArea transactionsArea;
    private int userId;

    public InvestmentPage(int userId) {
        this.userId = userId;
        setTitle("Gestion d'Investissement");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        loadUserData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        infoPanel.add(new JLabel("Total Investi :"));
        totalInvestedLabel = new JLabel("Calcul en cours...");
        infoPanel.add(totalInvestedLabel);

        infoPanel.add(new JLabel("Solde Actuel :"));
        currentBalanceLabel = new JLabel("Calcul en cours...");
        infoPanel.add(currentBalanceLabel);

        transactionsArea = new JTextArea(10, 30);
        transactionsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(transactionsArea);
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton investButton = new JButton("Investir");
        JButton withdrawButton = new JButton("Retirer");
        investButton.addActionListener(this::invest);
        withdrawButton.addActionListener(this::withdraw);
        buttonPanel.add(investButton);
        buttonPanel.add(withdrawButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadUserData() {
        try {
            double totalInvested = PostgresSQLConfig.getTotalInvested(userId);
            double currentBalance = PostgresSQLConfig.getCurrentBalance(userId);
            java.util.List<String> transactions = PostgresSQLConfig.getTransactions(userId);

            totalInvestedLabel.setText("$" + totalInvested);
            currentBalanceLabel.setText("$" + currentBalance);
            transactions.forEach(transaction -> transactionsArea.append(transaction + "\n"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void invest(ActionEvent e) {
        // Créer le panneau pour le formulaire d'investissement avec les détails bancaires
        JPanel investPanel = new JPanel(new GridLayout(5, 2));
        JTextField investAmountField = new JTextField();
        JTextField bankNameField = new JTextField();
        JTextField transitNumberField = new JTextField();
        JTextField institutionNumberField = new JTextField();
        JTextField accountNumberField = new JTextField();

        investPanel.add(new JLabel("Montant à investir :"));
        investPanel.add(investAmountField);
        investPanel.add(new JLabel("Nom de la banque :"));
        investPanel.add(bankNameField);
        investPanel.add(new JLabel("Numéro de transit :"));
        investPanel.add(transitNumberField);
        investPanel.add(new JLabel("Numéro d'institution :"));
        investPanel.add(institutionNumberField);
        investPanel.add(new JLabel("Numéro de compte :"));
        investPanel.add(accountNumberField);

        // Afficher le formulaire dans une boîte de dialogue
        int result = JOptionPane.showConfirmDialog(this, investPanel, "Formulaire d'Investissement", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Traitement lorsque l'utilisateur clique sur "OK"
            String inputAmount = investAmountField.getText();
            String bankName = bankNameField.getText();
            String transitNumber = transitNumberField.getText();
            String institutionNumber = institutionNumberField.getText();
            String accountNumber = accountNumberField.getText();

            // Vérification de la validité du montant à investir
            if (!inputAmount.isEmpty()) {
                try {
                    double amount = Double.parseDouble(inputAmount);
                    if (amount < 100) {
                        JOptionPane.showMessageDialog(this, "Le montant minimum autorisé est de 100$", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                        return; // Arrêter le traitement en cas d'erreur
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un montant valide.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                    return; // Arrêter le traitement en cas d'erreur
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un montant à investir.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                return; // Arrêter le traitement en cas d'erreur
            }

            // Vérification des autres champs (nom de la banque, numéro de transit, etc.)
            // Ajouter des vérifications similaires pour chaque champ avec des messages d'erreur appropriés

            // Si toutes les vérifications sont réussies, effectuer l'investissement
            try {
                double oldBalance = PostgresSQLConfig.getCurrentBalance(userId);
                PostgresSQLConfig.updateInvestment(userId, Double.parseDouble(inputAmount));
                double newBalance = PostgresSQLConfig.getCurrentBalance(userId);

                if (newBalance != oldBalance) {
                    loadUserData();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'opération.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'investissement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void withdraw(ActionEvent event) {
        String input = JOptionPane.showInputDialog(this, "Montant à retirer :");
        try {
            double amount = Double.parseDouble(input);
            double currentBalance = PostgresSQLConfig.getCurrentBalance(userId);
            if (amount <= currentBalance) {
                PostgresSQLConfig.updateInvestment(userId, -amount);
                loadUserData();
            } else {
                JOptionPane.showMessageDialog(this, "Solde insuffisant pour le retrait.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du retrait : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main (String[]args){
            SwingUtilities.invokeLater(() -> {
                new InvestmentPage(1).setVisible(true);
            });
        }
    }