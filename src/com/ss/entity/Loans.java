package com.ss.entity;

public class Loans {
    private Integer bookId;
    private Integer branchId;
    private Integer cardNo;
    private String dateOut;
    private String dueDate;
    private String dateIn;
    private String title;

    public Loans(Integer bookId, Integer branchId, Integer cardNo, String dateOut, String dueDate, String dateIn, String title){
        this.bookId = bookId;
        this.branchId = branchId;
        this.cardNo = cardNo;
        this.dateOut = dateOut;
        this.dueDate = dueDate;
        this.dateIn = dateIn;
        this.title = title;
    }


    public Integer getBookId(){
        return bookId;
    }
    public Integer getBranchId(){
        return branchId;
    }
    public Integer getLoanCardNo(){
        return cardNo;
    }
    public String getDateOut(){return dateOut;}
    public String getDueDate(){return dueDate;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String dateIn(){return dateIn;}


}
