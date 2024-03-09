package DAO;
import modele.Client;

import modele.Investisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import view.PostgresSQLConfig;
import view.PasswordHashing;
import view.PasswordUtils;

public class ClientDao {

    public boolean registerClient(Client client) {
        // Générez un salt
        String salt = PasswordUtils.generateSalt();
        // Hachez le mot de passe avec le salt généré
        String hashedPassword = PasswordUtils.hashPassword(client.getPassword(), salt);

        String insertSQL = "INSERT INTO clients (fullName, email, password, salt, phoneNumber, creditScore, birthDate, maritalStatus, yearsInCanada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, client.getFullName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, salt); // Stockez le salt dans la base de données
            preparedStatement.setString(5, client.getPhoneNumber());
            preparedStatement.setInt(6, client.getCreditScore());
            preparedStatement.setString(7, client.getBirthDate());
            preparedStatement.setString(8, client.getMaritalStatus());
            preparedStatement.setString(9, client.getYearsInCanada());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
