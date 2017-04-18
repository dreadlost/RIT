package org.uit.rit.entity;


public class Bank extends Currency {
    String name;
    int numberAccount;

    public Bank(int summ, String currencyName, String name, int numberAccount) {
        super(summ, currencyName);
        this.name = name;
        this.numberAccount = numberAccount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(int numberAccount) {
        this.numberAccount = numberAccount;
    }


}
