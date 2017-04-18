package org.uit.rit.entity;


public class Monetary extends Active {

    Bank bank;
    Cash cash;


    public Monetary(int id, String name, Bank bank) {
        super(id, name);
        this.bank = bank;
    }

    public Monetary(int id, String name, Cash cash) {
        super(id, name);
        this.cash = cash;
    }

    public Bank getBank() {

        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

}
