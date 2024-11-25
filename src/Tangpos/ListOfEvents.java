package Tangpos;

import java.util.Scanner;

public class ListOfEvents {
    Scanner sc = new Scanner(System.in);
    
    public void listingevents(){
        boolean exit = true;
        while(exit){
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("Event");
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("Select Choice:");
            System.out.println("1. Add Event");
            System.out.println("2. Edit Event");
            System.out.println("3. Delete Event");
            System.out.println("4. View");
            System.out.println("5. Go Back");
            System.out.println("+-----------------------------------------------------------------+");
            int option;
            while(true){
                System.out.print("Enter (1-5): ");
                try{
                    option = sc.nextInt();
                    break;
                }catch(Exception e){
                    sc.next();
                    System.out.println("Enter a valid Integer!");
                }
            }
            switch(option){
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    edit();
                    break;
                case 3:
                    view();
                    delete();
                    break;
                case 4:
                    view();
                    break;
                default:
                    exit = false;
                    break;
            }
        }
    }
    public void add(){
        System.out.println("+--------------------+");
        System.out.printf("|%-20s|\n","List An Event");
        System.out.println("+--------------------+");
        System.out.print("Event Name: ");
        String name = sc.next();
        System.out.print("Event Theme: ");
        String theme = sc.next();
        System.out.print("Event Date: ");
        String date = sc.next();
        double price;
        while(true){
            System.out.print("Price: ");
            try{
                price = sc.nextInt();
                if(price>0){
                    break;
                }
            }catch(Exception e){
                sc.next();
                System.out.println("Enter a Valid Amount!");
            }
        }
        
        String sql = "INSERT INTO tbl_Events (E_Name, E_Theme, E_Date, E_Price) Values(?,?,?,?)";
        config conf = new config();
        conf.addRecord(sql, name, theme, date, price);
    }
    public void edit(){
        System.out.println("+--------------------+");
        System.out.printf("|%-20s|\n","List An Event");
        System.out.println("+--------------------+");
        int id;
        while(true){
            System.out.print("Enter ID: ");
            try{
                id = sc.nextInt();
                break;
            }catch(Exception e){
                sc.next();
                System.out.println("Enter a valid Integer!");
            }
        }
        System.out.print("Event Name: ");
        String name = sc.next();
        System.out.print("Event Theme: ");
        String theme = sc.next();
        System.out.print("Event Date: ");
        String date = sc.next();
        double price;
        while(true){
            System.out.print("Price: ");
            try{
                price = sc.nextInt();
                if(price>0){
                    break;
                }
            }catch(Exception e){
                sc.next();
                System.out.println("Enter a Valid Amount!");
            }
        }
         
        String confirmation;
        while (true) {
            System.out.print("Are you sure you want to update the record with ID " + id + "? (Y/N): ");
            confirmation = sc.next();

            if (confirmation.equals("Y") || confirmation.equals("y")) {
                String sql = "UPDATE tbl_Events SET E_Name = ?, E_Theme = ?, E_Date = ?, E_Price = ? Where E_Id = ?";
                config conf = new config();
                conf.updateRecord(sql, name, theme, date, price, id);
                break;
            } else if (confirmation.equals("N") || confirmation.equals("n")) {
                System.out.println("Update cancelled.");
                break;
            } else {
                System.out.println("Please enter 'Y' for yes or 'N' for no.");
            }
        }

    }
    public void delete(){
             int id;
             String confirmation;
            while (true) {
                System.out.print("Enter ID: ");
                try {
                    id = sc.nextInt();
                    break;
                } catch (Exception e) {
                    sc.next();
                    System.out.println("Enter a valid Integer!");
                }
            }
            
            while (true) {
                System.out.print("Are you sure you want to delete the record with ID " + id + "? (Y/N): ");
                confirmation = sc.next();

                if (confirmation.equals("Y")) {
                    String sql = "DELETE FROM tbl_Events WHERE E_Id = ?";
                    config conf = new config();
                    conf.deleteRecord(sql, id);
                    break;
                } else if (confirmation.equals("N")) {
                    System.out.println("Deletion cancelled.");
                    break;
                } else {
                    System.out.println("Please enter 'Y' for yes or 'N' for no.");
                }
            }
        }
    public static void view(){
        String tbl_view = "SELECT * FROM tbl_Events";
        String[] tbl_Headers = {"ID", "Event Name", "Event Theme", "Event Date", "Price"};
        String[] tbl_Columns = {"E_Id", "E_Name", "E_Theme", "E_Date", "E_Price"};
        config conf = new config();
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);
    }
}