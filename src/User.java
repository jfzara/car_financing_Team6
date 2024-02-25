public class User {
    private String userFullName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;
    private String userOccupation;
    private String userYearlyIncome;

    public User(String userFullName, String userEmail, String userPassword, String userPhoneNumber, String userOccupation, String userYearlyIncome) {
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userOccupation = userOccupation;
        this.userYearlyIncome = userYearlyIncome;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserYearlyIncome() {
        return userYearlyIncome;
    }

    public void setUserYearlyIncome(String userYearlyIncome) {
        this.userYearlyIncome = userYearlyIncome;
    }

    public String toString() {
        return "User{" +
                "userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userOccupation='" + userOccupation + '\'' +
                ", userYearlyIncome='" + userYearlyIncome + '\'' +
                '}';
    }
}