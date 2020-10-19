package com.company;

import com.ss.entity.Branch;
import com.ss.service.AdministratorService;
import com.ss.service.LibrarianService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibrarianMenu {
    public static void librarianMenu(){
        System.err.println("1)Enter Branch you manage");
        System.err.println("2)Quit to Previous");
        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    LibrarianService LS = new LibrarianService();
                    LS.selectBranch();
                    librarianMenu();
                    break;
                case 2:
                    Main.main(null);
                    break;
                default:
                    System.out.println("Please input one of the options above");
                    librarianMenu();
                    break;
            }

        }catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
            librarianMenu();
        }

    }

    public void lib2(Integer branchId, String branchName, String branchAddress){
        System.err.println("1) Update the details of the Library");
        System.err.println("2) Add copies of Book to the Branch");
        System.err.println("3) Quit to Previous");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch (menuSelection) {
                case 1:
                    lib3(branchId, branchName, branchAddress);
                    break;
                case 2:
                    lib4(branchId, branchName, branchAddress);
                    break;
                case 3:
                    librarianMenu();
                    break;
                default:
                    System.out.println("Please input one of the options given above");
                    lib2(branchId, branchName, branchAddress);
                    break;
            }

        }catch(InputMismatchException | SQLException e){
            System.out.println("Please enter an integer value");
            lib2(branchId, branchName, branchAddress);
        }

    }

    public void lib3(Integer branchId, String branchName, String branchAddress){
        System.out.println("You have chosen to update the Branch with BranchId: "+ branchId + " and Branch Name: " + branchName);
        System.err.println("Please enter new branch name or enter N/A for no change: ");
        try{
            Scanner scan = new Scanner(System.in);
            String newBranchName = scan.nextLine();
            if (newBranchName.equals("N/A") || newBranchName.equals("n/a") ) {
                newBranchName = branchName;
            }
            System.err.println("Please enter new branch address or enter N/A for no change: ");
            String newBranchAddress = scan.nextLine();
            if (newBranchAddress.equals("N/A")  || newBranchAddress.equals("n/a") ){
                newBranchAddress = branchAddress;
            }

            LibrarianService LS = new LibrarianService();
            Branch branch = new Branch(branchId, newBranchName, newBranchAddress);
            LS.updateBranchLib(branch);

            lib2(branchId, newBranchName, newBranchAddress);

        }catch(InputMismatchException | SQLException e){
           System.out.println("Please input a string value");
           lib3(branchId, branchName, branchAddress);
        }


    }

    public void lib4(Integer branchId, String branchName, String branchAddress) throws SQLException {
        LibrarianService LS = new LibrarianService();
        LS.updateCopies(branchId);
        lib2(branchId, branchName, branchAddress);

    }

}
