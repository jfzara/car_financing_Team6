package view;

import modele.Client;
import modele.Investisseur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostgresSQLConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        String createClientsTableSQL = "CREATE TABLE IF NOT EXISTS clients (" +
                "id SERIAL PRIMARY KEY," +
                "fullName VARCHAR(255)," +
                "email VARCHAR(255) UNIQUE," +
                "password_hash VARCHAR(255)," +
                "salt VARCHAR(255)," +
                "phoneNumber VARCHAR(20)," +
                "occupation VARCHAR(100)," +
                "yearlyIncome DECIMAL" +
                ");";

        String createInvestisseursTableSQL = "CREATE TABLE IF NOT EXISTS investisseurs (" +
                "id SERIAL PRIMARY KEY," +
                "fullName VARCHAR(255)," +
                "email VARCHAR(255) UNIQUE," +
                "phoneNumber VARCHAR(20)," +
                "bankName VARCHAR(255)," +
                "bankAccountDetails VARCHAR(255)" +
                ");";

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {

            statement.execute(createClientsTableSQL);
            statement.execute(createInvestisseursTableSQL);
            System.out.println("Tables 'clients' and 'investisseurs' created or already exist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Client getClientByEmail(String email) throws SQLException {
        String sql = "SELECT email, password_hash, salt FROM public.clients WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    byte[] salt = rs.getBytes("salt");
                    String passwordHash = rs.getString("password_hash");

                    return new Client(null, email, passwordHash, null, null, null, 0, null, null, null, salt);
                }
            }
        }
        return null;
    }



    public static Investisseur getInvestisseurByEmail(String email) {
        String sql = "SELECT email, password_hash, salt FROM clients WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                String retrievedEmail = resultSet.getString("email");
                String retrievedPassword = resultSet.getString("password_hash");

                return new Investisseur(retrievedEmail, retrievedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
