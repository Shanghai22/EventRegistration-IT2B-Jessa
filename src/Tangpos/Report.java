package Tangpos;

import java.util.Scanner;

public class Report {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    public void report(){
        System.out.println("\tEvents");
        String tbl_view = "SELECT * FROM tbl_Events";
        String[] tbl_Headers = {"Event ID", "Event Name", "Event Theme", "Event Date", "Price"};
        String[] tbl_Columns = {"E_Id", "E_Name", "E_Theme", "E_Date", "E_Price"};
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);
        
        int id;
        while(true){
            System.out.print("Enter Event ID: ");
            try{
                id = sc.nextInt();
                break;
            }catch(Exception e){
                sc.next();
                System.out.println("Enter a valid Integer!");
            }
        }
        String tbl_view4 = "SELECT * FROM tbl_Register Where E_Id = "+id;
        String[] tbl_Headers4 = {"ID", "First Name", "Last Name", "Gender", "Payment"};
        String[] tbl_Columns4 = {"R_Id", "R_fname", "R_lname", "R_gender", "R_Payment"};
        conf.viewRecords(tbl_view4, tbl_Headers4, tbl_Columns4);
    }
}
