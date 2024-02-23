public class Client extends User {
    // Nouveaux champs spécifiques aux clients
    private int creditScore;
    private String birthDate;
    private String maritalStatus;
    private String yearsInCanada;

    // Constructeur pour les clients
    public Client(String fullName, String email, String password, String phoneNumber, String occupation, String yearlyIncome,
                  int creditScore, String birthDate, String maritalStatus, String yearsInCanada) {
        // Appel du constructeur de la classe User
        super(fullName, email, password, phoneNumber, occupation, yearlyIncome);
     // Nouveaux champs spécifiques aux clients
        this.creditScore = creditScore;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
        this.yearsInCanada = yearsInCanada;
    }

    // Getters et setters pour les nouveaux champs
    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getYearsInCanada() {
        return yearsInCanada;
    }

    public void setYearsInCanada(String yearsInCanada) {
        this.yearsInCanada = yearsInCanada;
    }
}
