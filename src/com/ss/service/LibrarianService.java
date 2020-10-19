package com.ss.service;

import com.company.LibrarianMenu;
import com.ss.DAO.BranchDAO;
import com.ss.DAO.CopiesDAO;
import com.ss.entity.Branch;
import com.ss.entity.Copies;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibrarianService {

    public ConnectionUtil conUtil = new ConnectionUtil();

    public String selectBranch() throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);
            List<Branch> a = brdao.readAllBranches();
            conn.commit();
            Integer i;
            for (i = 0; i < a.size(); i++) {
                System.out.println((i + 1) + ") "+ a.get(i).getBranchName() + "," + a.get(i).getBranchAddress());
            }

            System.err.println((a.size() + 1) + ") Quit to Previous");
            try{
                Scanner scan = new Scanner(System.in);
                Integer branchOption = scan.nextInt();
                scan.nextLine();
                if(branchOption == (a.size() + 1)){
                    LibrarianMenu.librarianMenu();
                }else if (branchOption < a.size()){
                    Integer branchId = a.get(branchOption - 1).getBranchId();
                    String branchName = a.get(branchOption -1).getBranchName();
                    String branchAddress = a.get(branchOption -1).getBranchAddress();
                   LibrarianMenu lm = new LibrarianMenu();
                   lm.lib2(branchId, branchName, branchAddress);

                }else{
                    System.out.println("Please input one of the options above");
                }

            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
                selectBranch();
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
            }
        }
    }

    public String updateBranchLib(Branch branch) throws SQLException{
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);

            brdao.updateBranch(branch);

            conn.commit();
            System.out.println("Branch updated Successfully");
            return "Branch updated successfully";

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            return "Unable to update Branch - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String updateCopies(Integer branchId) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            CopiesDAO cdao = new CopiesDAO(conn);
            List<Copies> a = cdao.readBranchCopies(branchId);
            conn.commit();
            Integer i;
            System.err.println("Pick the Book you want to add copies of, to your branch: ");
            for (i = 0; i < a.size(); i++) {
                System.out.println((i + 1) + ") "+ a.get(i).getTitle());
            }

            System.err.println((a.size() + 1) + ") Quit to Previous");
            try{

                Scanner scan = new Scanner(System.in);
                Integer copiesOption = scan.nextInt();
                scan.nextLine();
                if(copiesOption == (a.size() + 1)){
                    LibrarianMenu.librarianMenu();
                }else if (copiesOption <= a.size()){
                    Integer copiesNum = a.get(copiesOption -1).getCopies();
                    if (copiesNum == null){
                        copiesNum = 0;
                    }
                    System.out.println("Existing number of copies: " + copiesNum);
                    System.err.println("Enter new number of copies: ");
                    Integer newCount = scan.nextInt();
                    scan.nextLine();

                    Integer bookId = a.get(copiesOption -1).getBookId();

                    cdao = new CopiesDAO(conn);
                    cdao.setNewCopies(branchId, bookId, newCount);


                }else{
                    System.out.println("Please input one of the options above");
                }

            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
                updateCopies(branchId);
            }

            conn.commit();
            System.out.println("Copies inserted successfully");
            return "Copies inserted successfully";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Unable to update copies - contact admin.");
            return "Unable to read branches - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }


}
