package com.joinposter.enums;

public enum TableHeader {

    NAME("Название"),
    TYPE("Тип"),
    BALANCE("Баланс");

    String name;

    TableHeader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}