package com.javaschool.telegrambot.inmemory;

import com.javaschool.telegrambot.models.Criterion;

import java.util.List;

public interface Info {
    List<Criterion> getUserCriterionData(int userId);
    void saveUserCriterionData(int userId, Criterion userCriterionData);
}
