package Tangpos;

import java.util.Scanner;

public class Report {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
  public void report() {
    while (true) {
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("\nEvent Report Menu:");
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("1. View All Events");
        System.out.println("2. View Specific Event");
        System.out.println("3. Exit");
        System.out.println("+-----------------------------------------------------------------+");

        int choice = 0; 
        while (true) {
            System.out.print("Choose an option: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
                break; 
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                sc.next(); 
            }
        }

        switch (choice) {
            case 1:
                viewAllEvents();
                break;
            case 2:
                ListOfEvents.view(); 
                viewSpecificEvent();
                break;
            case 3:
                System.out.println("Exiting Report Menu...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

    private void viewAllEvents() {
        System.out.println("\n\tAll Events:");
        String tbl_view = "SELECT * FROM tbl_Events";
        String[] tbl_Headers = {"Event ID", "Event Name", "Event Theme", "Event Date", "Price"};
        String[] tbl_Columns = {"E_Id", "E_Name", "E_Theme", "E_Date", "E_Price"};
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);
    }
private int getValidIntegerInput(String prompt) {
    int value = -1;
    while (true) {
        System.out.print(prompt);
        if (sc.hasNextInt()) {
            value = sc.nextInt();
            sc.nextLine(); 
            return value;
        } else {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.nextLine(); 
        }
        
    }       
}
    private void viewSpecificEvent() {
        
        int id = getValidIntegerInput("\nEnter Event ID to view details: ");
        

        String tbl_view = "SELECT * FROM tbl_Events WHERE E_Id = " + id;
        String[] tbl_Headers = {"Event ID", "Event Name", "Event Theme", "Event Date", "Price"};
        String[] tbl_Columns = {"E_Id", "E_Name", "E_Theme", "E_Date", "E_Price"};
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);

       
        System.out.println("\n\tParticipants for Event ID: " + id);
        String tbl_view4 = "SELECT * FROM tbl_Register WHERE E_Id = " + id;
        String[] tbl_Headers4 = {"ID", "First Name", "Last Name", "Gender", "Payment"};
        String[] tbl_Columns4 = {"R_Id", "R_fname", "R_lname", "R_gender", "R_Payment"};
        conf.viewRecords(tbl_view4, tbl_Headers4, tbl_Columns4);
    }
}
