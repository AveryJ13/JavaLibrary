package com.company;

import com.ss.entity.Branch;
import com.ss.entity.Genre;
import com.ss.service.AdministratorService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenuBranch {
    public static void adminMenuBranch(){
        AdministratorService AS = new AdministratorService();

        System.err.println("1) Add a new branch");
        System.err.println("2) Update an existing branch");
        System.err.println("3) Delete a branch");
        System.err.println("4) Read all branches");
        System.err.println("5) Back");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    System.err.println("Please enter the name of the new branch");
                    String branchName = scan.nextLine();
                    System.err.println("Please enter the address of the new branch");
                    String branchAddress = scan.nextLine();
                    Branch branch = new Branch(null, branchName, branchAddress);
                    AS.addBranch(branch);
                    AdminMenu.main(null);
                    break;
                case 2:
                    AS.readBranches();
                    System.err.println("Please enter the id of the branch you wish to edit");
                    Integer branchId = scan.nextInt();
                    scan.nextLine();
                    System.err.println("Please enter the new name of the branch");
                    branchName = scan.nextLine();
                    System.err.println("Please enter the new address of the branch");
                    branchAddress = scan.nextLine();

                    branch = new Branch(branchId, branchName, branchAddress);
                    AS.updateBranch(branch);
                    AdminMenu.main(null);
                    break;
                case 3:
                    AS.deleteBranch();
                    AdminMenu.main(null);
                    break;
                case 4:
                    AS.readBranches();
                    adminMenuBranch();
                    break;
                case 5:
                    AdminMenu.main(null);
                    break;
                default:
                    System.out.println("Please input a valid entry");
                    adminMenuBranch();
                    break;
            }
        }catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
        }
    }

}
