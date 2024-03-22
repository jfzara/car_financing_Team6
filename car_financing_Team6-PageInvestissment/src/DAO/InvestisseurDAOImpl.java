
package DAO;
import modele.Investisseur;

import modele.Investisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.InvestisseurDao;
import util.ConnectionUtil;
public class InvestisseurDaoImpl implements InvestisseurDao {

    @Override
    public int insert(Investisseur investisseur) {

        Connection conn = ConnectionUtil.getConnection();


        String sql = "INSERT INTO investisseur (fullName, email, password, phoneNumber, bankName, bankAccountDetails, investorRiskLevel, investorEducationLevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, investisseur.getFullName());
            stmt.setString(2, investisseur.getEmail());
            stmt.setString(3, investisseur.getPassword());
            stmt.setString(4, investisseur.getPhoneNumber());
            stmt.setString(5, investisseur.getBankName());
            stmt.setString(6, investisseur.getBankAccountDetails());
            stmt.setString(7, investisseur.getInvestorRiskLevel());
            stmt.setString(8, investisseur.getInvestorEducationLevel());


            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Un nouvel investisseur a été inséré avec succès.");
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de l'investisseur : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return -1;
    }



    public Investisseur findByEmail(String email) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM investisseur WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Investisseur(
                            rs.getString("fullName"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("phoneNumber"),
                            rs.getString("bankName"),
                            rs.getString("bankAccountDetails"),
                            rs.getString("investorRiskLevel"),
                            rs.getString("investorEducationLevel")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'investisseur par email : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }


}
