package com.javaschool.telegrambot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Wrapper {
    List<Long> chatIds;
    Advertisement advertisement;
}
