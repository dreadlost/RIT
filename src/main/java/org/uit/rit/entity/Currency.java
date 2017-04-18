package org.uit.rit.entity;


public class Currency {
    private int summ;
    private String currencyName;

    public Currency(int summ, String currencyName) {
        this.summ = summ;
        this.currencyName = currencyName;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
