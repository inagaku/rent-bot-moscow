package com.javaschool.restapiserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persons", schema="public")
public class Person {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Integer id;
//
    @Column(name = "id")
    int userId;

    @Column(name = "chat_id")
    Long chatId;

    @OneToOne
    @JoinColumn(name = "crit_id", nullable = false)
    Criterion crit;

    public Person(int userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
        this.crit = new Criterion();
    }
}
