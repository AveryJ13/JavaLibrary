package com.ss.entity;

public class Copies {
    private Integer bookId;
    private Integer branchId;
    private Integer copies;
    private String title;

    public Copies(Integer bookId, Integer branchId, Integer copies, String title){
        this.bookId = bookId;
        this.branchId = branchId;
        this.copies = copies;
        this.title = title;
    }

    public Integer getBranchId(){
        return branchId;
    }

    public Integer getBookId(){
        return bookId;
    }

    public Integer getCopies(){
        return copies;
    }

    public String getTitle(){return title;}


}
