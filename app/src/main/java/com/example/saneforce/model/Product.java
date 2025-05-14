package com.example.saneforce.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_code")
    private String productCode;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("product_unit")
    private String productUnit;

    @SerializedName("convQty")
    private String convQty;

    private double rate;

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public String getConvQty() {
        return convQty;
    }

    public double getRate() {
        return rate;
    }

    // Setters
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public void setConvQty(String convQty) {
        this.convQty = convQty;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
