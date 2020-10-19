package com.ss.entity;

import java.util.List;

public class Book {
    private Integer bookId;
    private String title;
    private List<Author> authors;
    private Integer genre;
    private Integer branchId;
    //	private List<Genre> genres;
//    private List<Branch> branches;
//    private Publisher publisher;
    private Integer publisherId;
    public Integer getBookId() {
        return bookId;
    }
    public Book(Integer bookId, String title, Integer publisherId) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
    }
//    public void setBookPublisher(Integer Publisher){this.publisher = Publisher}
    public void setBranchId(Integer branchId){this.bookId = bookId;}
    public Integer getBranchId(){return branchId; }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getPublisherId() {return publisherId; }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(Integer genre) { this.genre = genre;}
    public Integer getGenre() {return genre;}
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
