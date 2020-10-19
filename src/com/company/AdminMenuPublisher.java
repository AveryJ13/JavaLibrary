package com.company;

import com.ss.entity.Genre;
import com.ss.entity.Publisher;
import com.ss.service.AdministratorService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenuPublisher {
    public static void adminMenuPublisher(){

        AdministratorService AS = new AdministratorService();

        System.err.println("1) Add a new publisher");
        System.err.println("2) Update an existing publisher");
        System.err.println("3) Delete a publisher");
        System.err.println("4) Read all publishers");
        System.err.println("5) Back");

        try{
            Scanner scan = new Scanner(System.in);
            Integer menuSelection = scan.nextInt();
            scan.nextLine();

            switch(menuSelection){
                case 1:
                    System.err.println("Please enter the name of the new publisher");
                    String publisherName = scan.nextLine();
                    System.err.println("Please enter the address of the new publisher");
                    String publisherAddress = scan.nextLine();
                    System.err.println("Please enter the Phone Number(###-###-####) of the new publisher");
                    String publisherPhone = scan.nextLine();

                    Publisher publisher = new Publisher(null, publisherName, publisherAddress, publisherPhone);
                    AS.addPublisher(publisher);
                    AdminMenu.main(null);
                    break;
                case 2:
                    AS.readPublishers();
                    System.err.println("Please enter the id of the publisher you wish to edit");
                    Integer publisherId = scan.nextInt();
                    scan.nextLine();

                    System.err.println("Please enter the new name of the publisher");
                    publisherName = scan.nextLine();
                    System.err.println("Please enter the address of the new publisher");
                    publisherAddress = scan.nextLine();
                    System.err.println("Please enter the Phone Number(###-###-####) of the new publisher");
                    publisherPhone = scan.nextLine();
                    scan.nextLine();

                    publisher = new Publisher(publisherId, publisherName, publisherAddress, publisherPhone);
                    AS.editPublisher(publisher);
                    AdminMenu.main(null);
                    break;
                case 3:
                    AS.deletePublisher();
                    AdminMenu.main(null);
                    break;
                case 4:
                    AS.readPublishers();
                    adminMenuPublisher();
                    break;
                case 5:
                    AdminMenu.main(null);
                    break;
                default:
                    System.out.println("Please input a valid entry");
                    adminMenuPublisher();
                    break;
            }
        }catch(InputMismatchException | SQLException e){
            System.out.println("Please input an integer value");
        }


    }
}
