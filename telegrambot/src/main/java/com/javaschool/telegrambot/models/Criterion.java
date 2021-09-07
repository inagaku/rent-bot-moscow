package com.javaschool.telegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Criterion {
    double startValue;
    double endValue;
    List<String> subway;
}
