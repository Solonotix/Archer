import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class FoodVendor {
    public DbManager db = null;
    public final Scanner scnr = new Scanner(System.in);

    public void initialize(String fName) throws IOException, SQLException {
        /* TODO: impletement your method here. */
        // Connect to the database file
        db = new DbManager();
        if (db.connect(fName)) {
            System.out.println("Connection to " + fName + " is successful.");
        }

        // Initialize the database if necessary
        File file = new File(fName);
        if (!file.exists()) {
            if (db.initialize(fName)) {
                System.out.println("Initialization of " + fName
                        + " is successful.");
            }
            else {
                System.out.println("Error initializing the database.");
                db.disconnect();
                db = null;
            }
        }
    }
}
