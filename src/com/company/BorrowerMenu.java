package com.company;

import com.ss.service.BorrowerService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BorrowerMenu {
    public static void borrowerMenu(){
        System.err.println("Enter your Card Number: ");

        try{
            Scanner scan = new Scanner(System.in);
            Integer cardNumber = scan.nextInt();
            scan.nextLine();
            BorrowerService BS = new BorrowerService();
            BS.checkBorrowerCard(cardNumber);


        }catch(InputMismatchException | SQLException e){
            System.out.println("Please insert an integer value");
            borrowerMenu();
        }

    }

    public static void borr1(Integer cardNumber){
        System.err.println("1) Check out a book");
        System.err.println("2) Return a Book");
        System.err.println("3) Quit to Previous");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuOption = scan.nextInt();
            scan.nextLine();

            switch(menuOption){
                case 1:
                    BorrowerService BS = new BorrowerService();
                    BS.selectBorrowerBranch(cardNumber);
                    break;
                case 2:
                    BS = new BorrowerService();
                    BS.readBorrowerLoans(cardNumber);
                    break;
                case 3:
                    Main.main(null);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    borr1(cardNumber);
                    break;
            }

        }catch(InputMismatchException | SQLException e){
            System.out.println("Please insert an integer value");
            borr1(cardNumber);
        }
    }


}
