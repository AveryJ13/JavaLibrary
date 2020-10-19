package com.ss.entity;

public class Borrower {
    private Integer cardNo;
    private String name;
    private String address;
    private String phone;

    public Borrower(Integer cardNo, String name, String address, String phone){
        this.cardNo = cardNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getCardNo(){return cardNo;}
    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getPhone(){return phone;}

    public void setCardNo(Integer cardNo){this.cardNo = cardNo;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setPhone(String phone){this.phone = phone;}

}
