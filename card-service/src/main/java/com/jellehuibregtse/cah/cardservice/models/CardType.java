package com.jellehuibregtse.cah.cardservice.models;

public enum CardType {
    BLACK {
        @Override
        public String toString() {
            return "Black Card";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "White Card";
        }
    }
}
