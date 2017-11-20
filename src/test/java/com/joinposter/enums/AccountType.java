package com.joinposter.enums;

public enum AccountType {
    NO_CASH("Безналичный счет"),
    BANK_CARD("Банковская карта"),
    CASH("Наличные");

    private String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AccountType getEnum(String name) {
        for (AccountType type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return name;
    }
}
