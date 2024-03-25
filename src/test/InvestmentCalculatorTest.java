package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import view.InvestmentCalculator;

public class InvestmentCalculatorTest {



    @Test
    public void testMontantNegative() {
        // Test avec un montant d'investissement négatif.
        double amount = -1000.0;
        double rate = 0.05;
        int years = 1;
        // Calculez l'intérêt attendu en utilisant la formule même pour un montant négatif.
        double expectedInterest = amount * Math.pow(1 + rate, years) - amount;

        // Appel et résultat comme précédemment.
        double actualInterest = InvestmentCalculator.calculateInterest(amount, rate, years);

        // Vérifiez que l'intérêt calculé est correct même pour un montant négatif.
        assertEquals(expectedInterest, actualInterest, 0.001, "L'intérêt calculé avec un montant négatif doit correspondre à l'attendu.");
    }

    @Test
    public void testInteretNegative() {
        // Test avec un taux d'intérêt négatif.
        double amount = 1000.0;
        double rate = -0.01; // Taux d'intérêt négatif de -1%
        int years = 1;
        // L'intérêt attendu est calculé avec la formule standard, même avec un taux négatif.
        double expectedInterest = amount * Math.pow(1 + rate, years) - amount;

        // Appel et résultat comme précédemment.
        double actualInterest = InvestmentCalculator.calculateInterest(amount, rate, years);

        // Vérifiez que l'intérêt calculé correspond à l'attendu, même avec un taux négatif.
        assertEquals(expectedInterest, actualInterest, 0.001, "L'intérêt calculé avec un taux d'intérêt négatif doit correspondre à l'attendu.");
    }

}
