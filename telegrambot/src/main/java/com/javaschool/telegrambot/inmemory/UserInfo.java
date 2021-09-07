package com.javaschool.telegrambot.inmemory;

import com.javaschool.telegrambot.models.Criterion;
import com.javaschool.telegrambot.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserInfo implements Info {
    private Map<Integer, List<Criterion>> usersInfoData = new HashMap<>();
    private Map<Integer, List<Person>> usersInfoDataPerson = new HashMap<>();

    @Override
    public List<Criterion> getUserCriterionData(int userId) {
        List<Criterion> userInfoData = usersInfoData.get(userId);
        if (userInfoData == null) {
            userInfoData = new ArrayList<Criterion>();
        }
        return userInfoData;
    }

    @Override
    public void saveUserCriterionData(int userId, Criterion userCriterionData) {
        usersInfoData.computeIfAbsent(userId, k -> new ArrayList<>()).add(userCriterionData);
    }

    public Criterion getLastCrit(int userId){
        List<Criterion> temp = getUserCriterionData(userId);
        int len = temp.size() - 1;
        return temp.get(len);
    }

}
