import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class DbManager {
    // A Connection object being used throughout the program
    private Connection conn = null;

    public void connect(String fileName) {
        /* TODO: impletement your method here. */

    }

    public void disconnect() {
        /* TODO: impletement your method here. */

    }

    public void createMenuTable(String dataFile) throws IOException {
        /* TODO: impletement your method here. */

    }

    public void insertMenu(String category, String itemName, String description, double price, int time, int availability) {
        /* TODO: impletement your method here. */

    }

    public void createUserTable(String dataFile) throws IOException {
        /* TODO: impletement your method here. */

    }

    public void insertUser(String userName, String account, String firstName, String lastName, String email, String phoneNum, String password, String emId, String cardNum, String cardDate, String address, int points, int history) {
        /* TODO: impletement your method here. */

    }
}
