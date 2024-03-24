/*package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class InvestmentPageTest {

    @Test
    public void testInvestissementMontantInférieurMinimum() {
        InvestmentPage page = new InvestmentPage(1);
        String inputAmount = "50";

        page.invest(inputAmount);

        assertTrue(page.getErrorMessage().contains("Le montant minimum autorisé est de 100$"));
    }

    @Test
    public void testInvestissementMontantValide() {
        InvestmentPage page = new InvestmentPage(1);
        String inputAmount = "150";

        page.invest(inputAmount);

        assertTrue(page.getErrorMessage().contains("Veuillez entrer un numéro de transit valide (5 chiffres)."));
    }
}*/