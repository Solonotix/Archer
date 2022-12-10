import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException  {
        String fileName;
        // Create a FoodVendor object
        FoodVendor vendor = new FoodVendor();
        fileName = vendor.scnr.next();   // User input "vendor.db" as database file name.
        vendor.initialize(fileName);

        /* Part 1 of Milestone 1 */

        // Test if database named vendor.db is created successfully. You can use any SQL tool to view this file.
        File tempFile = new File(fileName);
        if(tempFile.exists()) {
            System.out.println("Database file (vendor.db) created successfully.");
        }

        vendor.db.disconnect();
    }
}