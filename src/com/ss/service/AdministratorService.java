package com.ss.service;

import com.company.AdminMenu;
import com.company.Main;
import com.ss.DAO.*;
import com.ss.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdministratorService {
    public ConnectionUtil conUtil = new ConnectionUtil();

    public String readBooks() throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);
            AuthorDAO adao = new AuthorDAO(conn);

            List<Book> e = bdao.readAllBooks();
            List<Author> a = adao.readAllAuthors();
            conn.commit();
            for (int i = 0; i < e.size(); i++) {

                System.out.println("ID: "+ e.get(i).getBookId() + " Title: " + e.get(i).getTitle());
            }

            return "Books read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read books - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readGenres() throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            GenreDAO gdao = new GenreDAO(conn);
            List<Genre> a = gdao.readAllGenres();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {

                System.out.println("ID: "+ a.get(i).getGenreId() + " Genre Name: " + a.get(i).getGenreName());
            }

            return "Authors read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read authors - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readBorrowers() throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BorrowerDAO bwdao = new BorrowerDAO(conn);
            List<Borrower> a = bwdao.readAllBorrowers();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {

                System.out.println("CardNo: "+ a.get(i).getCardNo() + " Name: " + a.get(i).getName() + " Address: " + a.get(i).getAddress() + " Phone: " + a.get(i).getPhone());
            }

            return "Borrowers read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read authors - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readUserLoans(Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            LoansDAO ldao = new LoansDAO(conn);
            List<Loans> a = ldao.readCurrentUserLoans(cardNo);
            conn.commit();
            System.out.println("Book loans currently out: ");
            for (int i = 0; i < a.size(); i++) {
                System.out.println("ID: "+ a.get(i).getBookId() + " dueDate: " + a.get(i).getDueDate());
            }

            if(a.size() >= 1){
                return "Books are Due";
            }else{
                return "";
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read authors - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }


    public String readAllLoans() throws SQLException {
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            LoansDAO ldao = new LoansDAO(conn);
            List<Loans> a = ldao.readAllLoans();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {
                System.out.println("cardNo: " + a.get(i).getLoanCardNo()+ "  BookId:  "+ a.get(i).getBookId() +"  dateOut: " + a.get(i).getDateOut() +  "  dueDate: " + a.get(i).getDueDate());
            }

            return "loans read ";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read loans - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readAuthors() throws SQLException{
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            List<Author> a = adao.readAllAuthors();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {

                System.out.println("ID: "+ a.get(i).getAuthorId() + " Author Name: " + a.get(i).getAuthorName());
            }

            return "Authors read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to read authors - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readPublishers() throws SQLException{
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);
            List<Publisher> a = pdao.readAllPublishers();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {
                System.out.println("ID: "+ a.get(i).getPublisherId() + " Publisher Name: " + a.get(i).getPublisherName() + " Publisher Address: " + a.get(i).getPublisherAddress() +
                        " Publisher Phone No: " + a.get(i).getPublisherPhone());
            }

            return "Publishers read successfully";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Unable to edit book - contact admin.");
            return "Unable to edit book - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String readBranches() throws SQLException{
        Connection conn = null;
        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);
            List<Branch> a = brdao.readAllBranches();
            conn.commit();
            for (int i = 0; i < a.size(); i++) {
                System.out.println("ID: "+ a.get(i).getBranchId() + " Branch Name: " + a.get(i).getBranchName() + " Branch Address: " + a.get(i).getBranchAddress());
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

    public String addGenre(Genre genre) throws SQLException{
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            GenreDAO gdao = new GenreDAO(conn);

            gdao.addGenre(genre);

            conn.commit();
            System.out.println("Genre added Successfully");
            return "Genre added successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update book - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String updateBook(Book book) throws SQLException {
        Connection conn = null;

        try{
            conn = conUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);

            if(book.getPublisherId() == null){
                bdao.updateBookWithoutPublisher(book);
            }else{
                bdao.updateBookWPublisher(book);
            }

            System.err.println("Would you like to update the author of the book as well?");
            System.err.println("1) Yes");
            System.err.println("2) No");
            try{
                Scanner scan = new Scanner(System.in);
                Integer authorUpdate = scan.nextInt();
                scan.nextLine();
                switch(authorUpdate){
                    case 1:
                        readAuthors();
                        System.err.println("Please enter the id of the author your book now belongs too");
                        Integer authorId = scan.nextInt();
                        AuthorDAO adao = new AuthorDAO(conn);
                        adao.updateBookAuthors(book.getBookId(), authorId);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Not a valid option");
                        AdminMenu.adminUpdateBookAuthor();
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer value");
                AdminMenu.adminUpdateBookAuthor();
            }



            conn.commit();
            System.out.println("Book Updated Successfully");
            AdminMenu.main(null);
            return "Book added successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update book - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String deleteGenre() throws SQLException{
        Connection conn = null;
        try{
            conn = conUtil.getConnection();

            readGenres();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the genre Id you wish to delete");
                Integer genreId = scan.nextInt();
                scan.nextLine();
                GenreDAO gdao = new GenreDAO(conn);
                gdao.deleteGenre(genreId);
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            }

            conn.commit();

            AdminMenu.main(null);
            return "Genre Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to Delete Genre - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String deleteBook() throws SQLException{
        Connection conn = null;

        try{
            conn = conUtil.getConnection();

            readBooks();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the book Id you wish to delete, if the bookId is not valid, no operation will occur");
                Integer deleteId = scan.nextInt();
                scan.nextLine();
                BookDAO bdao = new BookDAO(conn);
                bdao.deleteBook(deleteId);
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            }





            conn.commit();

            AdminMenu.main(null);
            return "Author Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to Delete Book - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String deleteAuthor() throws SQLException{
        Connection conn = null;

        try{
            conn = conUtil.getConnection();

            readAuthors();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the Author Id you wish to delete");
                Integer deleteId = scan.nextInt();
                scan.nextLine();
                AuthorDAO adao = new AuthorDAO(conn);
                adao.deleteAuthor(deleteId);
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            }





            conn.commit();
            System.out.println("Author Updated Successfully");
            AdminMenu.main(null);
            return "Author Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Author - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String updateGenre(Genre genre) throws SQLException {
        Connection conn = null;

        try{
            conn = conUtil.getConnection();
            GenreDAO gdao = new GenreDAO(conn);
            gdao.updateGenre(genre);



            conn.commit();
            System.out.println("Genre Updated Successfully");
            AdminMenu.main(null);
            return "Genre Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Genre - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }


    public String updateAuthor(Author author) throws SQLException {
        Connection conn = null;

        try{
            conn = conUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            adao.updateAuthor(author);



            conn.commit();
            AdminMenu.main(null);
            return "Author Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Author - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String addBook(Book book) throws SQLException {
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);

            PublisherDAO pdao = new PublisherDAO(conn);

            book.setBookId(bdao.addBookWithPk(book));


            readAuthors();



            Boolean isRunning = true;
            Scanner scan = new Scanner(System.in);
            AuthorDAO adao = new AuthorDAO(conn);
            while(isRunning){
                System.err.println("Enter the id of at least one author.(0 to exit)");
                Integer authorId = scan.nextInt();
                scan.nextLine();
                if (authorId == 0){
                    isRunning = false;
                    break;
                }
                adao.addBookAuthors(book.getBookId(), authorId);

            }


            System.err.println("Enter the id of the genre your new book belongs too");
            readGenres();

            GenreDAO gdao = new GenreDAO(conn);
            Integer genreId = scan.nextInt();
            scan.nextLine();
            book.setGenre(genreId);

           gdao.setBookGenre(book);

           readBranches();

           Boolean isRunning2 = true;
           BranchDAO brdao = new BranchDAO(conn);
            while(isRunning2){
                System.err.println("Enter the id of at least one Branch.(0 to exit)");
                Integer branchId = scan.nextInt();
                scan.nextLine();

                if (branchId == 0){
                    isRunning2 = false;
                    break;
                }
                System.err.println("Number of copies to add to this branch");
                Integer noOfCopies = scan.nextInt();
                scan.nextLine();

                brdao.setBookCopies(book.getBookId(), branchId, noOfCopies);

            }
            scan.nextLine();
            conn.commit();
            System.out.println("Book and Author added successfully");
            return "Book added successfully";
        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to add book - contact admin.";
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String addPublisher(Publisher publisher) throws SQLException{
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);

            pdao.addPublisher(publisher);

            conn.commit();
            System.out.println("Publisher added Successfully");
            return "Publisher added successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to add Publisher - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String editPublisher(Publisher publisher) throws SQLException{
        Connection conn = null;

        try{
            conn = conUtil.getConnection();
            PublisherDAO gdao = new PublisherDAO(conn);
            gdao.updatePublisher(publisher);

            conn.commit();
            System.out.println("Publisher Updated Successfully");
            return "Publisher Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Publisher - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String deletePublisher() throws SQLException{
        Connection conn = null;

        try{
            conn = conUtil.getConnection();

            readPublishers();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the Publisher Id you wish to delete, if Id value is not found no operation will occur");
                Integer deleteId = scan.nextInt();
                scan.nextLine();
                PublisherDAO pdao = new PublisherDAO(conn);
                pdao.deletePublisher(deleteId);
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            }

            conn.commit();
            return "Publisher Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to delete Publisher - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String addBranch(Branch branch) throws SQLException{
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);

            brdao.addBranch(branch);

            conn.commit();
            System.out.println("Branch added Successfully");
            return "Branch added successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to add Branch - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String updateBranch(Branch branch) throws SQLException{
        Connection conn = null;
        readBranches();
        try {
            conn = conUtil.getConnection();
            BranchDAO brdao = new BranchDAO(conn);

            brdao.updateBranch(branch);

            conn.commit();
            System.out.println("Branch updated Successfully");
            return "Branch updated successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Branch - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String deleteBranch() throws SQLException{
        Connection conn = null;

        try{
            conn = conUtil.getConnection();

            readBranches();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the Branch Id you wish to delete");
                Integer deleteId = scan.nextInt();
                scan.nextLine();
                BranchDAO brdao = new BranchDAO(conn);
                brdao.deleteBranch(deleteId);
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            }





            conn.commit();
            return "Branch Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to delete Publisher - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String addBorrower(Borrower borrower) throws SQLException{
        Connection conn = null;

        try {
            conn = conUtil.getConnection();
            BorrowerDAO bwdao = new BorrowerDAO(conn);

            bwdao.addBorrower(borrower);

            conn.commit();
            System.out.println("Borrower added successfully");
            return "Borrower added successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to add Branch - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String updateBorrower(Borrower borrower) throws SQLException{
        Connection conn = null;
        readBorrowers();
        try {
            conn = conUtil.getConnection();
            BorrowerDAO bwdao = new BorrowerDAO(conn);

            bwdao.updateBorrower(borrower);

            conn.commit();
            System.out.println("Borrower updated Successfully");
            return "Borrower updated successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Branch - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public String deleteBorrower() throws SQLException{
        Connection conn = null;
        try{
            conn = conUtil.getConnection();

            readBorrowers();
            try{
                Scanner scan = new Scanner(System.in);
                System.err.println("Please select the Borrower cardNo you wish to delete");
                Integer deleteId = scan.nextInt();
                scan.nextLine();
                String loanCheck = readUserLoans(deleteId);
                if (loanCheck.length() > 5){
                    System.out.println("This user currently has books checked out");
                }else{
                    System.out.println("Borrower successfully deleted");
                    BorrowerDAO bwdao = new BorrowerDAO(conn);
                bwdao.deleteBorrower(deleteId);
                }

//
            }catch(InputMismatchException e){
                System.out.println("Please input an integer value");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            conn.commit();

            return "Branch Updated Successfully";

        }catch ( SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to delete Publisher - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String overrideDueDate(Integer cardNo, Integer bookId) throws SQLException {
        Connection conn = null;

        try{
            conn = conUtil.getConnection();
            LoansDAO ldao = new LoansDAO(conn);
            ldao.overideDueDate(cardNo, bookId);



            conn.commit();
            return "Due Date Updated Successfully";

        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
            if (conn != null) {
                conn.rollback();
            }
            Main.main(null);
            return "Unable to update Author - contact admin.";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
