package com.ss.DAO;

import java.sql.*;
import java.util.Scanner;


public class GetAllAuthors {
    public static String driverName = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/library";
    public static String userName = "root";
    public static String password = "Avery0077";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.err.println("Enter a Author Name to search: ");
        String authorName = scan.nextLine();

        try{
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, userName, password);
            //Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("select * from tbl_author where authorName = ?");
            pstmt.setString(1, authorName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.println("Author Name: " + rs.getString("authorName"));
                System.out.println("Author Id: " + rs.getInt("authorId"));
            }
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
