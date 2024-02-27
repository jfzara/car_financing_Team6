package modele;
public class Investisseur {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String bankName;
    private String bankAccountDetails;
    private String investorRiskLevel;
    private String investorEducationLevel;


    public Investisseur(String fullName, String email, String password, String phoneNumber, String bankName, String bankAccountDetails,
                        String investorRiskLevel, String investorEducationLevel) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.bankAccountDetails = bankAccountDetails;
        this.investorRiskLevel = investorRiskLevel;
        this.investorEducationLevel = investorEducationLevel;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getInvestorRiskLevel() {
        return investorRiskLevel;
    }

    public void setInvestorRiskLevel(String investorRiskLevel) {
        this.investorRiskLevel = investorRiskLevel;
    }

    public String getInvestorEducationLevel() {
        return investorEducationLevel;
    }

    public void setInvestorEducationLevel(String investorEducationLevel) {
        this.investorEducationLevel = investorEducationLevel;
    }

    // Méthode toString pour obtenir une représentation textuelle de l'investisseur
    @Override
    public String toString() {
        return "Investisseur{" +
                "Nom complet='" + fullName + '\'' +
                ", Email='" + email + '\'' +
                ", Numéro de téléphone='" + phoneNumber + '\'' +
                ", Nom de la banque='" + bankName + '\'' +
                ", Détails du compte bancaire='" + bankAccountDetails + '\'' +
                ", Niveau de risque='" + investorRiskLevel + '\'' +
                ", Niveau d'éducation='" + investorEducationLevel + '\'' +
                '}';
    }
}