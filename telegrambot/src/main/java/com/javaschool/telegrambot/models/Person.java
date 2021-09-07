package com.javaschool.telegrambot.models;

import lombok.Data;

@Data
public class Person {
    int userId;
    Long chatId;
    //String name;
    Criterion crit;

    public Person(int userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
        this.crit = new Criterion();
    }
}
