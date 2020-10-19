package com.company;

import com.ss.DAO.AuthorDAO;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to the SS Library Management System. Which category of a user are you? ");
        System.err.println("1)Librarian");
        System.err.println("2)Administrator");
        System.err.println("3)Borrower");
        System.err.println("4)Exit");
        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();

            switch(menuSelection){
                case 1:
                    LibrarianMenu.librarianMenu();
                    break;
                case 2:
                    AdminMenu.main(null);
                    break;
                case 3:
                    BorrowerMenu.borrowerMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input one of the available options");
                    main(null);
                    break;
            }
        } catch(InputMismatchException e){
            System.out.println("Please insert an Integer value");
            main(null);
        }

    }
}
