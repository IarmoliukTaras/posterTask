package com.joinposter.enums;

public enum Currency {

    UAH("Гривна", "₴"),
    RUS("Рубль", ""),
    USD("Доллар", "$");

    private String name;
    private String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Currency getEnum(String text) {
        for (Currency currency : values()) {
            if (currency.toString().equalsIgnoreCase(text)) {
                return currency;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return symbol.isEmpty() ? name : name + ", " + symbol;
    }
}
