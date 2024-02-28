

class Client {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String occupation;
    private String yearlyIncome;
    private int creditScore;
    private String birthDate;
    private String maritalStatus;
    private String yearsInCanada;


    public Client(String fullName, String email, String password, String phoneNumber, String occupation, String yearlyIncome,
                  int creditScore, String birthDate, String maritalStatus, String yearsInCanada) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.yearlyIncome = yearlyIncome;
        this.creditScore = creditScore;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
        this.yearsInCanada = yearsInCanada;
    }


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

    @Override
    public String toString() {
        return "Client{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditScore=" + creditScore +
                ", birthDate='" + birthDate + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", yearsInCanada='" + yearsInCanada + '\'' +
                '}';
    }
}

class Client {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String occupation;
    private String yearlyIncome;
    private int creditScore;
    private String birthDate;
    private String maritalStatus;
    private String yearsInCanada;


    public Client(String fullName, String email, String password, String phoneNumber, String occupation, String yearlyIncome,
                  int creditScore, String birthDate, String maritalStatus, String yearsInCanada) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.yearlyIncome = yearlyIncome;
        this.creditScore = creditScore;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
        this.yearsInCanada = yearsInCanada;
    }


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

    @Override
    public String toString() {
        return "Client{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditScore=" + creditScore +
                ", birthDate='" + birthDate + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", yearsInCanada='" + yearsInCanada + '\'' +
                '}';
    }
}

