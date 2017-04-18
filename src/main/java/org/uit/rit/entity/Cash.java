package org.uit.rit.entity;


public class Cash extends Currency {

    String name;

    public Cash(int summ, String currencyName, String name) {
        super(summ, currencyName);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
