package test;
import view.InvestmentService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InvestmentServiceTest {

    @Test
    public void AjouterSolde() {
        // Création d'une instance
        InvestmentService service = new InvestmentService();

        //  solde initial
        double currentBalance = 2000.0;

        //  montant de l'investissement
        double investmentAmount = 500.0;

        // Calcul du nouveau solde attendu, qui devrait être la somme du solde actuel et du montant de l'investissement.
        double expectedNewBalance = 2500.0;


        //  le nouveau solde après l'ajout du montant de l'investissement.
        double newBalance = service.addInvestmentToBalance(currentBalance, investmentAmount);

        assertEquals(expectedNewBalance, newBalance, "Le nouveau solde après investissement devrait être correct.");
    }

}