import java.sql.ResultSet;

public class Admin extends User {
    // TODO: Declare private fields here

    // TODO: Define overloaded constructor here
    public Admin() {
        super("");
    }

    public Admin(String userName) {
        super(userName);
    }

    public Admin(String username, String firstName) {
        super(username, firstName);
    }

    public Admin(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }

    public Admin(String username, String firstName, String lastName, String accountType) {
        super(username, firstName, lastName, accountType);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email) {
        super(username, firstName, lastName, accountType, email);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress) {
        super(username, firstName, lastName, accountType, email, billingAddress);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate, Integer rewardPoints) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate, rewardPoints);
    }

    public Admin(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate, Integer rewardPoints, Integer orderHistory) {
        super(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate, rewardPoints, orderHistory);
    }

    public static Admin fromResultSet(ResultSet row) {
        return (Admin)User.fromResultSet(row);
    }

    public static Admin fromRow(String row) {
        return (Admin)User.fromRow(row);
    }
}