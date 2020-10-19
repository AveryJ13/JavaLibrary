package com.ss.entity;

public class Branch {
    private Integer branchId;
    private String branchName;
    private String branchAddress;

    public Branch(Integer branchId, String branchName, String branchAddress){
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    public Integer getBranchId(){
        return branchId;
    }

    public String getBranchName(){
        return branchName;
    }

    public String getBranchAddress(){
        return branchAddress;
    }

    public void setBranchName(){
        this.setBranchName();
    }

    public void setBranchId(Integer branchId){
        this.branchId = branchId;
    }

    public void setBranchAddress(String branchAddress){
        this.branchAddress = branchAddress;
    }

}
