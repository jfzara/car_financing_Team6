package view;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class PostgresSQLConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Connexion à la base de données
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Initialisation de la base de données
    public static void initializeDatabase() {
        String createInvestorsTable = """
            CREATE TABLE IF NOT EXISTS investors (
            id SERIAL PRIMARY KEY,
            full_name VARCHAR(255) NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL,
            password_hash VARCHAR(255) NOT NULL,
            salt VARCHAR(255) NOT NULL,
            bank_name VARCHAR(255),
            bank_account_details TEXT,
            risk_level VARCHAR(50),
            education_level VARCHAR(50),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

        String createPortefeuillesTable = """
            CREATE TABLE IF NOT EXISTS portefeuilles (
            id SERIAL PRIMARY KEY,
            investor_id INT NOT NULL,
            nom VARCHAR(255) NOT NULL,
            description TEXT,
            FOREIGN KEY (investor_id) REFERENCES investors(id)
            );
            """;

        String createActifsTable = """
            CREATE TABLE IF NOT EXISTS actifs (
            id SERIAL PRIMARY KEY,
            nom VARCHAR(255) NOT NULL,
            description TEXT
            );
            """;

        String createInvestissementsActifsTable = """
            CREATE TABLE IF NOT EXISTS investissements_actifs (
            id SERIAL PRIMARY KEY,
            portefeuille_id INT NOT NULL,
            actif_id INT NOT NULL,
            montant_investi DECIMAL(10, 2) NOT NULL,
            date_achat TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (portefeuille_id) REFERENCES portefeuilles(id),
            FOREIGN KEY (actif_id) REFERENCES actifs(id)
            );
            """;

        String createTransactionsTable = """
            CREATE TABLE IF NOT EXISTS transactions (
            id SERIAL PRIMARY KEY,
            investor_id INT NOT NULL,
            montant DECIMAL(10, 2) NOT NULL,
            type VARCHAR(255) NOT NULL,
            date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
            description TEXT,
            FOREIGN KEY (investor_id) REFERENCES investors(id)
            );
            """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createInvestorsTable);
            stmt.execute(createPortefeuillesTable);
            stmt.execute(createActifsTable);
            stmt.execute(createInvestissementsActifsTable);
            stmt.execute(createTransactionsTable);
            System.out.println("Tables created or already exist.");
        } catch (SQLException e) {
            System.out.println("Error during database initialization: " + e.getMessage());
        }
    }

    public static double getTotalInvested(int investorId) {
        double totalInvested = 0.0;
        String sql = "SELECT SUM(montant) AS total_invested FROM transactions WHERE investor_id = ? AND type = 'Investissement';";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalInvested = rs.getDouble("total_invested");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total invested: " + e.getMessage());
        }
        return totalInvested;
    }


    // Ajoute une transaction pour un investisseur dans la table 'transactions'
    public static void addTransaction(int investorId, double montant, String type, String description) {
        String sql = "INSERT INTO transactions (investor_id, montant, type, date, description) VALUES (?, ?, ?, NOW(), ?);";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            pstmt.setDouble(2, montant);
            pstmt.setString(3, type);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtient la liste des transactions pour un investisseur
    public static List<String> getTransactions(int investorId) {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT montant, type, date, description FROM transactions WHERE investor_id = ? ORDER BY date DESC;";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    double montant = rs.getDouble("montant");
                    String type = rs.getString("type");
                    Timestamp date = rs.getTimestamp("date");
                    String description = rs.getString("description");
                    transactions.add(String.format("%s - %s$ - %s (%s)", date, montant, type, description));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    public static void createPortefeuille(int investorId, String nom, String description) {
        String sql = "INSERT INTO portefeuilles (investor_id, nom, description) VALUES (?, ?, ?);";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            pstmt.setString(2, nom);
            pstmt.setString(3, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateInvestment(int investorId, double amount) {

        String sql = "INSERT INTO transactions (investor_id, montant, type, date, description) VALUES (?, ?, ?, NOW(), ?);";

        String type = amount >= 0 ? "Investissement" : "Retrait";
        String description = type + " de " + Math.abs(amount);

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, type);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static double getCurrentBalance(int investorId) {
        double currentBalance = 0.0;
        String sql = "SELECT SUM(montant) AS current_balance FROM transactions WHERE investor_id = ?;";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, investorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    currentBalance = rs.getDouble("current_balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currentBalance;
    }

    public static void main(String[] args) {
        initializeDatabase();

    }
}
