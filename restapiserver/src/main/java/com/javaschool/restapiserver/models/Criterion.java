package com.javaschool.restapiserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "criteries", schema = "public")
public class Criterion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @Column(name = "start_value")
    double startValue;
    @Column(name = "end_value")
    double endValue;
    @Column
    String subway;
}
