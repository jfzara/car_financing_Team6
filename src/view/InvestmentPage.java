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
        String input = JOptionPane.showInputDialog(this, "Montant à investir :");
        if (input != null && !input.isEmpty()) {
            try {
                double amount = Double.parseDouble(input);
                double oldBalance = PostgresSQLConfig.getCurrentBalance(userId);
                PostgresSQLConfig.updateInvestment(userId, amount);
                double newBalance = PostgresSQLConfig.getCurrentBalance(userId);

                if (newBalance != oldBalance) {
                    loadUserData();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'opération.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
                loadUserData(); // Recharger les données utilisateur
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