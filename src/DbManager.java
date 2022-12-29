import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class DbManager {
    // A Connection object being used throughout the program
    private Connection conn = null;
    private final HashMap<String, FluentFile> files;
    private final HashMap<String, FluentFile> queries;

    public DbManager() {
        files = new HashMap<>();
        queries = new HashMap<>();

        for(FluentFile file : new FluentFile(System.getProperty("user.dir")).child("resources").child("queries").listFiles()) {
            queries.put(file.getName(false), file);
        }
        for(FluentFile file : new FluentFile(System.getProperty("user.dir")).child("resources").child("data").listFiles()) {
            files.put(file.getName(false), file);
        }
    }

    public boolean connect(String fileName) throws IOException, SQLException {
        FluentFile dbFile = new FluentFile(System.getProperty("user.dir"))
                .child("data")
                .child(fileName);

        dbFile.createNewFile();
        conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbFile.getAbsolutePath()));

        return true;
    }

    public void createMenuTable(String dataFile) throws IOException, SQLException {
        String query = this.getQuery("create-menu-table");
        conn.createStatement().executeUpdate(query);
        if(this.files.containsKey(dataFile)) {
            for(String row : this.getDelimitedFile(dataFile, "\n", true)) {
                MenuItem item = MenuItem.fromRow(row);
                this.insertMenu(item);
            }
        }
    }

    public void createUserTable(String dataFile) throws IOException, SQLException {
        String query = this.getQuery("create-user-table");
        conn.createStatement().executeUpdate(query);
        if(this.files.containsKey(dataFile)) {
            for(String row : this.getDelimitedFile(dataFile, "\n", true)) {
                User user = User.fromRow(row);
                this.insertUser(user);
            }
        }
    }

    public void disconnect() throws SQLException {
        if(this.conn != null) {
            this.conn.close();
        }
        this.conn = null;
    }

    public Admin getAdmin(String userName) {
        try(PreparedStatement statement = this.prepare("get-admin-user", userName)) {
            ResultSet results = statement.executeQuery();
            if(results.first()) {
                return Admin.fromResultSet(results);
            }
        }
        catch(IOException io) {
            System.out.printf("Unable to resolve PreparedStatement get-admin-user.%n%s%n", io);
        }
        catch(SQLException sql) {
            System.out.printf("Unable to resolve ResultSet from PreparedStatement in getAdmin().%n%s%n", sql);
        }

        return new Admin();
    }

    private String[] getDelimitedFile(String fileName, String delimiter, boolean hasHeader) throws IOException {
        String[] content = this.getFile(fileName).split(delimiter);
        return Arrays.stream(content)
                .skip(hasHeader ? 1 : 0)
                .toArray(String[]::new);
    }

    private String getFile(String fileName) throws IOException {
        return this.files.get(fileName).readFile();
    }

    private String getQuery(String fileName) throws IOException {
        return this.queries.get(fileName).readFile();
    }

    public boolean initialize(String fileName) {
        try {
            this.connect(fileName);
            this.createMenuTable("menu");
            this.createUserTable("user");
            return true;
        }
        catch (IOException ioException) {
            System.out.printf("Failed to create database file. %s%n", ioException.getMessage());
            return false;
        }
        catch (SQLException sqlException) {
            System.out.printf("Failed to perform database actions. %s%n", sqlException.getMessage());
            return false;
        }
    }

    public void insertMenu(MenuItem item) throws SQLException, IOException {
        this.insertMenu(item.getCategory(), item.getItemName(), item.getDescription(), item.getPrice(), item.getPrepTime(), item.getAvailable());
    }

    public void insertMenu(String category, String itemName, String description, double price, int time, int availability) throws IOException, SQLException {
        try(PreparedStatement statement = this.prepare("insert-menu-table", category, itemName, description, price, time, availability)) {
            statement.execute();
        }
    }

    public void insertUser(User user) throws IOException, SQLException {
        this.insertUser(
                user.getUserName(),
                user.getAccountType(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getEmployeeId(),
                user.getCreditCardNumber(),
                user.getCreditCardExpDate(),
                user.getBillingAddress(),
                user.getRewardPoints(),
                user.getOrderHistory()
        );
    }

    public void insertUser(String userName, String account, String firstName, String lastName, String email, String phoneNum, String password, String emId, String cardNum, String cardDate, String address, int points, int history) throws IOException, SQLException {
        try(PreparedStatement statement = this.prepare("insert-user-table", userName, account, firstName, lastName, email, phoneNum, password, emId, cardNum, cardDate, address, points, history)) {
            statement.execute();
        }
    }

    private PreparedStatement prepare(String query, Object ...args) throws IOException, SQLException {
        if(this.queries.containsKey(query)) {
            query = this.getQuery(query);
            System.out.printf("PreparedStatement(lookup) => %s%n", query);
        }

        PreparedStatement statement = conn.prepareStatement(query);

        for(int position = 0; position < args.length; position++) {
            int index = position + 1;
            Object obj = args[position];
            switch(obj) {
                case Boolean b -> statement.setBoolean(index, b);
                case Date dt -> statement.setDate(index, (java.sql.Date)dt);
                case Double d -> statement.setDouble(index, d);
                case Float f -> statement.setFloat(index, f);
                case Integer i -> statement.setInt(index, i);
                case Long l -> statement.setLong(index, l);
                case Short sh -> statement.setShort(index, sh);
                case String s -> statement.setString(index, s);
                default -> statement.setNull(index, Types.NULL);
            }
        }

        System.out.printf("PreparedStatement(end) => %s%n", statement);
        return statement;
    }
}
