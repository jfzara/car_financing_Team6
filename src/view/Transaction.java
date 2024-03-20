package view;
import java.time.LocalDate;

public class Transaction {
    private int id;
    private int investissementId;
    private double montant;
    private LocalDate date;
    private String type;

    public Transaction(int investissementId, double montant, String type) {
        this.investissementId = investissementId;
        this.montant = montant;
        this.date = LocalDate.now();
        this.type = type;
    }




}

