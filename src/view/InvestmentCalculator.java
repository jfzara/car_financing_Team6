package view;

public class InvestmentCalculator {

    public static double calculateInterest(double amount, double rate, int years) {
        return amount * Math.pow(1 + rate, years) - amount;
    }

}