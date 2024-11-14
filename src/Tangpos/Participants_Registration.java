package Tangpos;

import java.util.Scanner;
import java.sql.*;

public class Participants_Registration {
    Scanner sc = new Scanner(System.in);
    ListOfEvents loe = new ListOfEvents();
    config conf = new config();
    
    public void listingregistration(){
        boolean exit = true;
        while(exit){
            System.out.println("------------------");
            System.out.println("\tRegister A Participants");
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. Delete");
            System.out.println("4. View");
            System.out.println("5. Go Back");
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
        System.out.print("First Name: ");
        String fname = sc.next();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Gender: ");
        String gender = sc.next();
        loe.view();
        int id;
        while(true){
            System.out.print("Enter ID: ");
            try{
                id = sc.nextInt();
                if(checkID(id, conf)){
                    break;
                }
            }catch(Exception e){
                sc.next();
                System.out.println("Enter a valid Integer!");
            }
        }
        double profit = getProfit(id);
        double price = getPrice(id);
        double participants = getParticipants(id);
        profit += price;
        participants ++;
        String sql = "UPDATE tbl_Events SET E_Participants = ?, E_Profit = ? Where E_Id = ?";
        conf.updateRecord(sql, participants, profit, id);
        String sql2 = "INSERT INTO tbl_Register (E_Id, R_fname, R_lname, R_gender, R_Payment) Values(?,?,?,?,?)";
        conf.addRecord(sql2, id, fname, lname, gender, price);
    }
    public void edit(){
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
        System.out.print("First Name: ");
        String fname = sc.next();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Gender: ");
        String gender = sc.next();
        String confirmation;
        while (true) {
            System.out.print("Are you sure you want to update the record with ID " + id + "? (Y/N): ");
            confirmation = sc.next();

            if (confirmation.equals("Y") || confirmation.equals("y")) {
                String sql = "UPDATE tbl_Register SET R_fname = ?, R_lname = ?, R_gender = ? WHERE R_Id = ?";
                config conf = new config();
                conf.updateRecord(sql, fname, lname, gender, id);
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
        // Connfirm deletion
        while (true) {
            System.out.print("Are you sure you want to delete the record with ID " + id + "? (Y/N): ");
            confirmation = sc.next();

            if (confirmation.equals("Y")) {
                String sql = "DELETE From tbl_Register Where R_Id = ?";
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
    public void view(){
        String tbl_view = "SELECT * FROM tbl_Register";
        String[] tbl_Headers = {"ID", "First Name", "Last Name"};
        String[] tbl_Columns = {"R_Id", "R_fname", "R_lname"};
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);
    }
    
    private double getProfit(int id) {
        double profit = 0.0;
        String query = "SELECT E_Profit FROM tbl_Events WHERE E_Id = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                profit = rs.getDouble("E_Profit");
            }
        } catch (SQLException e) {
            System.out.println("|\tError retrieving balance: " + e.getMessage());
        }
        return profit;
    }
    private double getPrice(int id) {
        double price = 0.0;
        String query = "SELECT E_Price FROM tbl_Events WHERE E_Id = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("E_Price");
            }
        } catch (SQLException e) {
            System.out.println("|\tError retrieving balance: " + e.getMessage());
        }
        return price;
    }
    private double getParticipants(int id) {
        double num = 0.0;
        String query = "SELECT E_Participants FROM tbl_Events WHERE E_Id = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                num = rs.getDouble("E_Participants");
            }
        } catch (SQLException e) {
            System.out.println("|\tError retrieving balance: " + e.getMessage());
        }
        return num;
    }
    private boolean checkID(int id, config conf) {
        String query = "SELECT COUNT(*) FROM tbl_Events Where E_Id = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("|\tError checking Report ID: " + e.getMessage());
        }
        return false;
    }
}
