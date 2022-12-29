import java.sql.SQLException;
import java.sql.ResultSet;

public class User {
    private Integer id;
    private String userName;
    private String accountType;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String employeeId;
    private String creditCardNumber;
    private String creditCardExpDate;
    private String billingAddress;
    private Integer rewardPoints;
    private Integer orderHistory;

    public User(String userName) {
        this.userName = userName;
        this.accountType = "default";
        this.firstName = "first";
        this.lastName = "last";
        this.email = "none@not-provided.co";
        this.phoneNumber = "";
        this.password = "";
        this.employeeId = "";
        this.creditCardExpDate = "";
        this.creditCardNumber = "";
        this.billingAddress = "";
        this.rewardPoints = 0;
        this.orderHistory = 0;
    }

    public User(String username, String firstName) {
        this(username);
        this.firstName = firstName;
    }

    public User(String username, String firstName, String lastName) {
        this(username, firstName);
        this.lastName = lastName;
    }

    public User(String username, String firstName, String lastName, String accountType) {
        this(username, firstName, lastName);
        this.accountType = accountType;
    }

    public User(String username, String firstName, String lastName, String accountType, String email) {
        this(username, firstName, lastName, accountType);
        this.email = email;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress) {
        this(username, firstName, lastName, accountType, email);
        this.billingAddress = billingAddress;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber) {
        this(username, firstName, lastName, accountType, email, billingAddress);
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber);
        this.employeeId = employeeId;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId);
        this.password = password;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password);
        this.creditCardNumber = creditCardNumber;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber);
        this.creditCardExpDate = creditCardExpDate;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate, Integer rewardPoints) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate);
        this.rewardPoints = rewardPoints;
    }

    public User(String username, String firstName, String lastName, String accountType, String email, String billingAddress, String phoneNumber, String employeeId, String password, String creditCardNumber, String creditCardExpDate, Integer rewardPoints, Integer orderHistory) {
        this(username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate, rewardPoints);
        this.orderHistory = orderHistory;
    }

    public static User fromResultSet(ResultSet row) {
        User user = new User("default");

        try {
            user.userName = row.getString("user_name");
            user.accountType = row.getString("account_type");
            user.firstName = row.getString("first_name");
            user.lastName = row.getString("last_name");
            user.email = row.getString("email");
            user.phoneNumber = row.getString("phone_number");
            user.password = row.getString("password");
            user.employeeId = row.getString("employee_id");
            user.creditCardNumber = row.getString("credit_card_number");
            user.creditCardExpDate = row.getString("credit_card_exp_date");
            user.billingAddress = row.getString("billing_address");
            user.rewardPoints = row.getInt("reward_points");
            user.orderHistory = row.getInt("order_history");
        }
        catch(SQLException sql) {
            System.out.printf("Failed to build User from ResultSet.%n%s%n", sql);
        }

        return user;
    }

    public static User fromRow(String row) {
        String[] columns = row.split("\t");
        System.out.printf("User => '%s'%n", String.join("', '", columns));

        // username, firstName, lastName, accountType, email, billingAddress, phoneNumber, employeeId, password, creditCardNumber, creditCardExpDate, rewardPoints, orderHistory
        // user_name, account_type, first_name, last_name, email, phone_number, password, employee_id, credit_card_number, credit_card_exp_date, billing_address, reward_points, order_history
        return switch (columns.length) {
            case 1 -> new User(columns[0]);
            case 2 -> new User(columns[0], "", "", columns[1]);
            case 3 -> new User(columns[0], columns[2], "", columns[1]);
            case 4 -> new User(columns[0], columns[2], columns[3], columns[1]);
            case 5 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4]);
            case 6 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], "", columns[5]);
            case 7 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], "", columns[5], "", columns[6]);
            case 8 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], "", columns[5], columns[7], columns[6]);
            case 9 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], "", columns[5], columns[7], columns[6], columns[8]);
            case 10 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], "", columns[5], columns[7], columns[6], columns[8], columns[9]);
            case 11 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], columns[10], columns[5], columns[7], columns[6], columns[8], columns[9]);
            case 12 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], columns[10], columns[5], columns[7], columns[6], columns[8], columns[9], Integer.parseInt(columns[11]));
            case 13 -> new User(columns[0], columns[2], columns[3], columns[1], columns[4], columns[10], columns[5], columns[7], columns[6], columns[8], columns[9], Integer.parseInt(columns[11]), Integer.parseInt(columns[12]));
            default -> new User("default");
        };
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getAccountType() {
        return this.accountType;
    }
    
    public String getBillingAddress() {
        return this.billingAddress;
    }

    public String getCreditCardExpDate() {
        return this.creditCardExpDate;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Integer getOrderHistory() {
        return this.orderHistory;
    }

    public Integer getRewardPoints() {
        return this.rewardPoints;
    }
    
    public String getUserName() {
        return this.userName;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public void setAccountType(String value) {
        this.accountType = value;
    }

    public void setBillingAddress(String value) {
        this.billingAddress = value;
    }

    public void setCreditCardExpDate(String value) {
        this.creditCardExpDate = value;
    }

    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public void setEmployeeId(String value) {
        this.employeeId = value;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public void setOrderHistory(Integer value) {
        this.orderHistory = value;
    }

    public void setRewardPoints(Integer value) {
        this.rewardPoints = value;
    }

    public void setUserName(String value) {
        this.userName = value;
    }
}
