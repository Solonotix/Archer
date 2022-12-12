import java.io.File;
import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        // Create a FoodVendor object
        FoodVendor vendor = new FoodVendor();
        String fileName = vendor.scnr.next();   // User input "vendor.db" as database file name.
        vendor.initialize(fileName);

        /* Part 1 of Milestone 1 */
        // Test if database named vendor.db is created successfully. You can use any SQL tool to view this file.
        if(new File(fileName).exists()) {
            System.out.println("Database file (vendor.db) created successfully.");
        }

        vendor.db.disconnect();
    }
}