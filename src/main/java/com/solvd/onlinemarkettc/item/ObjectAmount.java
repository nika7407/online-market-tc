package com.solvd.onlinemarkettc.item;

public class ObjectAmount<K> {

    private int amount = 0;
    private K object;

    public ObjectAmount(K object, int amount) {
        this.object = object;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decrementAmount() {
        amount = --amount;
    }

    public K getObject(){
        return object;
    }

}

