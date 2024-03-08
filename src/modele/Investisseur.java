package modele;


public class Investisseur {
    private byte[] salt;
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
        this.salt = salt;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }
    public Investisseur(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public byte[] getSalt() {
        return this.salt;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public String getInvestorRiskLevel() {
        return investorRiskLevel;
    }

    public String getInvestorEducationLevel() {
        return investorEducationLevel;
    }



    @Override
    public String toString() {
        return "Investisseur{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountDetails='" + bankAccountDetails + '\'' +
                ", investorRiskLevel='" + investorRiskLevel + '\'' +
                ", investorEducationLevel='" + investorEducationLevel + '\'' +
                '}';
    }
}
