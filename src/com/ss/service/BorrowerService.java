package com.ss.service;

import com.company.BorrowerMenu;
import com.company.LibrarianMenu;
import com.company.Main;
import com.ss.DAO.*;
import com.ss.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BorrowerService {

    public ConnectionUtil conUtil = new ConnectionUtil();

    public String checkBorrowerCard(Integer cardNumber) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BorrowerDAO brdao = new BorrowerDAO(conn);
            List<Borrower> a = brdao.readSpecificBorrower(cardNumber);
            conn.commit();

            if (a.size() < 1) {
                System.out.println("Not a valid Card Number Pls try again");
                BorrowerMenu.borrowerMenu();
            } else {
                BorrowerMenu.borr1(cardNumber);
            }

            return "Authors read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            return "Unable to read authors - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String selectBorrowerBranch(Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);
            List<Branch> a = brdao.readAllBranches();
            conn.commit();
            Integer i;
            for (i = 0; i < a.size(); i++) {
                System.out.println((i + 1) + ") " + a.get(i).getBranchName() + "," + a.get(i).getBranchAddress());
            }

            System.err.println((a.size() + 1) + ") Quit to Previous");
            try {
                Scanner scan = new Scanner(System.in);
                Integer branchOption = scan.nextInt();
                scan.nextLine();
                if (branchOption == (a.size() + 1)) {
                    BorrowerMenu.borrowerMenu();
                } else if (branchOption <= a.size()) {
                    Integer branchId = a.get(branchOption - 1).getBranchId();
                    bookCheckoutSelection(cardNo, branchId);

                } else {
                    System.out.println("Please input one of the options above");
                    selectBorrowerBranch(cardNo);
                }

            } catch (InputMismatchException e) {
                System.out.println("Please input an integer value");
                selectBorrowerBranch(cardNo);
            }


            return "Branches read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Unable to read branches - contact admin.");
            return "Unable to read branches - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
                BorrowerMenu.borr1(cardNo);
            }
        }
    }

    public String bookCheckoutSelection(Integer cardNo, Integer branchId) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            CopiesDAO cdao = new CopiesDAO(conn);
            List<Copies> a = cdao.readBranchCopiesAvailable(branchId);
            conn.commit();
            Integer i;
            System.err.println("Pick the Book you want to check out: ");
            for (i = 0; i < a.size(); i++) {
                System.out.println((i + 1) + ") " + a.get(i).getTitle());
            }

            System.err.println((a.size() + 1) + ") Quit to Previous");
            try {

                Scanner scan = new Scanner(System.in);
                Integer checkoutOption = scan.nextInt();
                scan.nextLine();
                if (checkoutOption == (a.size() + 1)) {
                    BorrowerMenu.borrowerMenu();
                } else if (checkoutOption <= a.size()) {
                    Integer copiesNum = a.get(checkoutOption - 1).getCopies();
                    if (copiesNum == null) {
                        copiesNum = 0;
                    }


                    Integer bookId = a.get(checkoutOption - 1).getBookId();

                    BorrowerDAO BD = new BorrowerDAO(conn);
                    BD.checkoutBook(bookId, branchId, cardNo);

                    System.out.println("Book checked out successfully");

                } else {
                    System.out.println("Please input one of the options above");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please input an integer value");
                bookCheckoutSelection(cardNo, branchId);
            }

            conn.commit();
            return "Copies inserted successfully";
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("You have already checked out a copy of this book from this branch");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);

            return "Unable to checkout book - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readBorrowerLoans(Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            LoansDAO ldao = new LoansDAO(conn);
            List<Loans> a = ldao.readCheckedOut(cardNo);
            conn.commit();
            System.out.println("Book loans currently out: ");
            Integer i;
            for (i = 0; i < a.size(); i++) {
                System.out.println((i + 1) + ") " + a.get(i).getTitle() + " Due Date: " + a.get(i).getDueDate());
            }

            System.err.println((a.size() + 1) + ") Quit to Previous");
            try {

                Scanner scan = new Scanner(System.in);
                Integer returnOption = scan.nextInt();
                scan.nextLine();
                if (returnOption == (a.size() + 1)) {
                    BorrowerMenu.borrowerMenu();
                } else if (returnOption <= a.size()) {


                    Integer bookId = a.get(returnOption - 1).getBookId();
                    Integer branchId = a.get(returnOption -1).getBranchId();


                    System.out.println("You have chosen to return bookId: " + bookId + " Title: " + a.get(returnOption -1).getTitle() + " from branchId: " + branchId);
                    ldao = new LoansDAO(conn);
                    ldao.returnBook(bookId, branchId, cardNo);
                    conn.commit();
                    System.out.println("Loans updated successfully");
                } else {
                    System.out.println("Please input one of the options above");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please input an integer value");
                readBorrowerLoans(cardNo);
            }

            return "Loans read successfully";

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            return "Unable to read loans - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
                BorrowerMenu.borr1(cardNo);
            }
        }
    }





}
