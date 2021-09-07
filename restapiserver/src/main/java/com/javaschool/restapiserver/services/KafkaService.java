package com.javaschool.restapiserver.services;

import com.javaschool.restapiserver.models.Advertisement;
import com.javaschool.restapiserver.request.AdvertisementRestSender;
import com.javaschool.restapiserver.utils.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaService {

    @Autowired
    RandomUtil randomUtil;
    @Autowired
    AdvertisementRestSender advertisementRestSender;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(id = "cian", topics = "cian-flats")
    public void kafkaListener(String inputJson) {
        Advertisement advertisement = objectMapper.readValue(inputJson, Advertisement.class);
        advertisement.setPrice(randomUtil.between(40000, 160000));
        advertisementRestSender.postRequest(advertisement);
    }
}
