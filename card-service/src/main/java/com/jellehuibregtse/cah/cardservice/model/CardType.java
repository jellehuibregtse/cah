package com.jellehuibregtse.cah.cardservice.model;

public enum CardType {

    BLACK("Black card"), WHITE("White card");

    private final String text;

    CardType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
