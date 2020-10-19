package com.ss.entity;

import com.ss.DAO.BaseDAO;

import java.util.List;

public class Publisher  {
    private Integer publisherId;
    private String publisherName;
    private String publisherAddress;
    private String publisherPhone;

    public Publisher(Integer publisherId, String publisherName, String publisherAddress, String publisherPhone) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.publisherAddress = publisherAddress;
        this.publisherPhone = publisherPhone;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public String getPublisherName(){
        return publisherName;
    }

    public String getPublisherAddress(){
        return publisherAddress;
    }

    public String getPublisherPhone(){
        return publisherPhone;
    }

    public void setPublisherName(String publisherName){
        this.publisherName= publisherName;
    }

    public void setPublisherAddress(String publisherAddress){
        this.publisherAddress = publisherAddress;
    }

    public void setPublisherId(Integer authorId) {
        this.publisherId = publisherId;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

}
