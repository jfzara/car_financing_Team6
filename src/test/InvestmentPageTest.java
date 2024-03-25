package test;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import view.InvestmentPage;

public class InvestmentPageTest {

    @Test
    public void montantValide() {
        InvestmentPage page = new InvestmentPage(1);
        assertTrue(page.validateInvestmentAmount("200"),
                "La méthode devrait retourner true pour un montant valide d'investissement.");
    }

    @Test
    public void montantInvalid() {
        InvestmentPage page = new InvestmentPage(1);
        assertFalse(page.validateInvestmentAmount("-50"),
                " retourner false pour un montant négatif.");
    }

    @Test
    public void AmountIsNotANumber() {
        InvestmentPage page = new InvestmentPage(1);
        assertFalse(page.validateInvestmentAmount("abc"),
                " retourner false pour une entrée qui n'est pas un nombre.");
    }

    @Test
    public void AmountIsLessThanMinimum() {
        InvestmentPage page = new InvestmentPage(1);
        assertFalse(page.validateInvestmentAmount("50"),
                "retourner false pour un montant inférieur au minimum requis.");
    }
}
