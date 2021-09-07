package com.javaschool.restapiserver.utils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomUtil {

    private final Random random = new Random();

    public <T> T getRandomElement(List<T> list) {
        int i = between(0, list.size()-1);
        return list.get(i);
    }

    public int between(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
