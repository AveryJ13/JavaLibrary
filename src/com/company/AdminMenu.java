package com.company;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.DAO.BookDAO;
import com.ss.entity.Author;
import com.ss.entity.Book;
import com.ss.service.AdministratorService;

public class AdminMenu {

    public static void adminMenuOne() throws SQLException {

        AdministratorService AS = new AdministratorService();


        Scanner scan = new Scanner(System.in);
        System.err.println("1)Add Book and Author");
        System.err.println("2)Update Book and Author");
        System.err.println("3)Delete Book and Author");
        System.err.println("4)Read all Books and Authors");
        System.err.println("5)Back to Main");
        System.err.println("6)Exit");
        try{
            Integer menuSelection = scan.nextInt();
            scan.nextLine();
            switch(menuSelection){
                case 1:
                    System.err.println("Enter the id of the publisher for the new Book");
                    AS.readPublishers();


                    Integer publisherId = scan.nextInt();
                    scan.nextLine();

                    System.err.println("Enter in the name of the new book");

                    String bookTitle = scan.nextLine();

                    Book bk = new Book(null, bookTitle, publisherId);

                    AS.addBook(bk);
                    break;
                case 2:
                    adminUpdateBookAuthor();
                    break;
                case 3:
                    deleteBookAndAuthor();
                    break;
                case 4:
                    System.err.println("1) Read Books");
                    System.err.println("2) Read Authors");
                    System.err.println("3) Back to Admin");
                    menuSelection = scan.nextInt();
                    scan.nextLine();

                    switch(menuSelection){
                        case 1:
                            AS.readBooks();
                            System.err.println("1) Back to Admin");
                            System.err.println("2) Exit");
                            menuSelection = scan.nextInt();
                            scan.nextLine();
                            switch(menuSelection){
                                case 1:
                                    main(null);
                                    break;
                                case 2:
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Please input a valid option");
                                    break;
                            }
                            break;
                        case 2:
                            AS.readAuthors();
                            System.err.println("1) Back to Admin");
                            System.err.println("2) Exit");
                            menuSelection = scan.nextInt();
                            scan.nextLine();
                            switch(menuSelection){
                                case 1:
                                    main(null);
                                    break;
                                case 2:
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Please input a valid option");
                                    break;
                            }

                            break;

                    }

                        break;
                case 5:
                    main(null);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input one of the options above");
                    adminMenuOne();
                    break;
            }
        }catch(InputMismatchException e){

            System.out.println("Please input an integer value");
            adminMenuOne();
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("The server will not process your request, check your parameter values and try again");
            adminMenuOne();
        }






//        if(menuSelection == 4){
//            System.err.println("1) Read Books");
//            System.err.println("2) Read Authors");
//            System.err.println("Any) Back to Admin");
//            menuSelection = scan.nextInt();
//
//            if(menuSelection == 1){
//                AS.readBooks();
//                System.err.println("1) Back to Admin");
//                System.err.println("Any) Exit");
//                menuSelection = scan.nextInt();
//                if (menuSelection == 1){
//                    adminMenuOne();
//                }else{
//                    System.out.println("Bye");
//                }
//            }else if(menuSelection == 2){
//                AS.readAuthors();
//                System.err.println("1) Back to Admin");
//                System.err.println("Any) Exit");
//                menuSelection = scan.nextInt();
//                if (menuSelection == 1){
//                    adminMenuOne();
//                }else{
//                    System.out.println("Bye");
//                }
//            }else if(menuSelection == 3){
//                System.out.println("Bye");
//            }
//        }else if(menuSelection == 1){
//            System.err.println("Enter the id of the publisher for the new Book");
//            AS.readPublishers();
//
//            Integer publisherId = scan.nextInt();
//            scan.nextLine();
//
//            System.err.println("Enter in the name of the new book");
//
//            String bookTitle = scan.nextLine();
//
//            Book bk = new Book(null, bookTitle, publisherId);
//
//            AS.addBook(bk);
//
//
//        } else if (menuSelection == 5){
//            main(null);
//        }else if (menuSelection == 2){
//            adminUpdateBookAuthor();
//        }else if (menuSelection == 3){
//            deleteBookAndAuthor();
//        }
//        else{
//            System.out.println("Bye");
//        }


    }

    public static void adminUpdateBookAuthor(){

        AdministratorService AS = new AdministratorService();

        System.err.println("Would you like to update a book or an author?");
        System.err.println("1)Book");
        System.err.println("2)Author");
        System.err.println("3)Back");
        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    AS.readBooks();
                    try{

                        System.err.println("Please enter in the id of the book you want to update ");
                        Integer bookId = scan.nextInt();
                        scan.nextLine();
                        System.err.println("Please enter in the new title of the book");
                        String bookTitle = scan.nextLine();
                        System.err.println("Would you like to update the publisher for this book as well?");
                        System.err.println("1)Yes");
                        System.err.println("2)No");
                        Integer pubChoice = scan.nextInt();
                        scan.nextLine();
                        switch(pubChoice){
                            case 1:
                                AS.readPublishers();
                                System.err.println("Please enter in the id of the books new publisher");
                                Integer publisherId = scan.nextInt();
                                scan.nextLine();
                                Book book = new Book(bookId, bookTitle, publisherId);
                                AS.updateBook(book);
                                break;
                            case 2:
                                Book book2 = new Book(bookId, bookTitle, null);
                                AS.updateBook(book2);
                                break;
                            default:
                                System.out.println("Not a valid option");
                                adminUpdateBookAuthor();
                        }


                    } catch (InputMismatchException e){
                        System.err.println("Please input the correct value types");
                        adminUpdateBookAuthor();
                    }
                    break;
                case 2:
                    AS.readAuthors();
                    try{
                        System.err.println("Please enter in the id of the Author you want to update ");
                        Integer authorId = scan.nextInt();
                        scan.nextLine();
                        System.err.println("Please enter in the new author Name");
                        String authorName = scan.nextLine();
                        Author author = new Author(authorId, authorName);
                        AS.updateAuthor(author);
                    }catch(InputMismatchException e){
                        System.out.println(("Please enter an Integer value"));
                    }

                    break;
                case 3:
                    adminMenuOne();
                    break;
                default:
                    System.err.println("this is not a valid option");
                    adminUpdateBookAuthor();
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please input an integer value");
            adminUpdateBookAuthor();
        }

    }

    public static void deleteBookAndAuthor(){
        AdministratorService AS = new AdministratorService();

        System.err.println("Would you like to delete a book or an author?");
        System.err.println("1)Book");
        System.err.println("2)Author");
        System.err.println("3)Back");
        try{
            Scanner scan = new Scanner(System.in);
            Integer menuOption = scan.nextInt();
            switch(menuOption){
                case 1:
                    AS.deleteBook();
                    break;
                case 2:
                    AS.deleteAuthor();
                    break;
                case 3:
                    adminMenuOne();
                    break;
                default:
                    System.out.println("Not a valid entry");
                    deleteBookAndAuthor();
                    break;
            }
        } catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
            deleteBookAndAuthor();
        }

    }


    public static void main(String args[]) throws SQLException {


        System.err.println("1)Add/Update/Delete/Read Book and Author");
        System.err.println("2)Add/Update/Delete/Read Genres");
        System.err.println("3)Add/Update/Delete/Read Publishers");
        System.err.println("4)Add/Update/Delete/Read Library Branches");
        System.err.println("5)Add/Update/Delete/Read Borrowers");
        System.err.println("6)Over-ride Due Date for a Book Loan");
        System.err.println("7)Back to Main");
        System.err.println("8)Exit");
        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();

            switch(menuSelection){
                case 1:
                    adminMenuOne();
                    break;
                case 2:
                    AdminMenuGenre.adminMenuGenre();
                    break;
                case 3:
                    AdminMenuPublisher.adminMenuPublisher();
                    break;
                case 4:
                    AdminMenuBranch.adminMenuBranch();
                    break;
                case 5:
                    AdminMenuBorrower.adminMenuBorrower();
                    break;
                case 6:
                    AdministratorService AS = new AdministratorService();
                    AS.readAllLoans();
                    System.err.println("Please input the cardNo of the loan you wish to override, if invalid value is entered no change will be made");
                    Integer cardNo = scan.nextInt();
                    scan.nextLine();
                    System.err.println("Please input the BookNo of the loan you wish to override, if invalid value is entered no change will be made");
                    Integer bookId = scan.nextInt();
                    scan.nextLine();
                    AS.overrideDueDate(cardNo, bookId);
                    main(null);
                    break;
                case 7:
                    Main.main(null);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please insert a valid option");
                    main(null);
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Please insert an Integer value");
            AdminMenu.main(null);
        }


    }

}
