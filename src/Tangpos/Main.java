package Tangpos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        while(exit){
            System.out.println("+--------------------+");
            System.out.printf("|%-20s|\n","Event Registration");
            System.out.println("+--------------------+");
            System.out.println("1. List of Events");
            System.out.println("2. Participants Registration");
            System.out.println("3. Report");
            System.out.println("4. Exit");
            int option;
            while(true){
                System.out.print("Enter (1-4): ");
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
                    ListOfEvents listofevents = new ListOfEvents();
                    listofevents.listingevents();
                    break;
                case 2:
                    Participants_Registration register = new Participants_Registration();
                    register.listingregistration();
                    break;
                case 3:
                    Report r = new Report();
                    r.report();
                    break;
                default:
                    exit = false;
                    break;
            }
        }
    }
}
