package com.company;

import com.ss.entity.Borrower;
import com.ss.entity.Branch;
import com.ss.service.AdministratorService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenuBorrower {
    public static void adminMenuBorrower(){
        AdministratorService AS = new AdministratorService();

        System.err.println("1) Add a new borrower");
        System.err.println("2) Update an existing borrower");
        System.err.println("3) Delete a borrower");
        System.err.println("4) Read all borrowers");
        System.err.println("5) Back");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    System.err.println("Please enter the name of the new borrower");
                    String name = scan.nextLine();
                    System.err.println("Please enter the address of the new borrower");
                    String address = scan.nextLine();
                    System.err.println("Please enter the phone(###-###-####) of the new borrower");
                    String phone = scan.nextLine();
                    Borrower borrower = new Borrower(null, name, address, phone);
                    AS.addBorrower(borrower);
                    AdminMenu.main(null);
                    break;
                case 2:
                    AS.readBorrowers();
                    System.err.println("Please enter the cardNo of the borrower you wish to edit");
                    Integer branchId = scan.nextInt();
                    scan.nextLine();
                    System.err.println("Please enter the new name of the borrower");
                    name = scan.nextLine();
                    System.err.println("Please enter the new address of the borrower");
                    address = scan.nextLine();
                    System.err.println("Please enter the new phone of the borrower");
                    phone = scan.nextLine();

                    borrower = new Borrower(null, name, address, phone);
                    AS.updateBorrower(borrower);
                    AdminMenu.main(null);
                    break;
                case 3:
                    AS.deleteBorrower();
                    AdminMenu.main(null);
                    break;
                case 4:
                    AS.readBorrowers();
                    adminMenuBorrower();
                    break;
                case 5:
                    AdminMenu.main(null);
                    break;
                default:
                    System.out.println("Please input a valid entry");
                    adminMenuBorrower();
                    break;
            }
        }catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
        }
    }
}
