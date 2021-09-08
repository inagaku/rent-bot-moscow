package com.javaschool.telegrambot.listcommands;

public enum Commands {
    START_FILLING(""),
    SET_PRICE(""),
    SET_LOCATION(""),
    GET_APARTMENT(""),
    HELP("HELP"),
    START("START");

    private final String description;

    Commands(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "/" + this.name();
    }
}
