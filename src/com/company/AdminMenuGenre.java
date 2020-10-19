package com.company;

import com.ss.entity.Genre;
import com.ss.service.AdministratorService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenuGenre {
    public static void adminMenuGenre(){

        AdministratorService AS = new AdministratorService();

        System.err.println("1) Add a new genre");
        System.err.println("2) Update an existing genre");
        System.err.println("3) Delete a genre");
        System.err.println("4) Read all genres");
        System.err.println("5) Back");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    System.err.println("Please enter the name of the new genre");
                    String genreName = scan.nextLine();
                    Genre genre = new Genre(null, genreName);
                    AS.addGenre(genre);
                    AdminMenu.main(null);
                    break;
                case 2:
                    AS.readGenres();
                    System.err.println("Please enter the id of the genre you wish to edit");
                    Integer genreId = scan.nextInt();
                    scan.nextLine();
                    System.err.println("Please enter the new name of the genre");
                    genreName = scan.nextLine();
                    genre = new Genre(genreId, genreName);
                    AS.updateGenre(genre);
                    AdminMenu.main(null);
                    break;
                case 3:
                    AS.deleteGenre();
                    AdminMenu.main(null);
                    break;
                case 4:
                    AS.readGenres();
                    adminMenuGenre();
                    break;
                case 5:
                    AdminMenu.main(null);
                    break;
                default:
                    System.out.println("Please input a valid entry");
                    adminMenuGenre();
                    break;
            }
        }catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
        }


    }
}
