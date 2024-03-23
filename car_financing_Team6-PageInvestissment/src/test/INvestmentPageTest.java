package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class InvestmentPageTest {

    @Test
    public void testInvestissementMontantVide() {
        InvestmentPage page = new InvestmentPage(1);
        String inputAmount = "";

        page.invest(inputAmount);

        assertTrue(page.getErrorMessage().contains("Veuillez entrer un montant à investir."));
    }

    @Test
    public void testInvestissementNumeroTransitInvalide() {
        InvestmentPage page = new InvestmentPage(1);
        String inputAmount = "200";
        page.setTransitNumber("1234");

        page.invest(inputAmount);

        assertTrue(page.getErrorMessage().contains("Veuillez entrer un numéro de transit valide (5 chiffres)."));
    }
}