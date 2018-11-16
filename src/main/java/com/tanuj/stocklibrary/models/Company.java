package com.tanuj.stocklibrary.models;

/*
* Simple pojo class to represents the stock data
* */
public class Company {

    private String companyName;
    private String website;
    private String CEO;
    private String price;

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsite() {
        return website;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
