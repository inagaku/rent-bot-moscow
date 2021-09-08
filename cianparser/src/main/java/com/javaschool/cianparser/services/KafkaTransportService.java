package com.javaschool.cianparser.services;

import com.javaschool.cianparser.config.KafkaConfiguration;
import com.javaschool.cianparser.model.Advertisement;
import com.javaschool.cianparser.services.parsers.CianExtractorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class KafkaTransportService {

    @Autowired
    CianExtractorService extractorService;

    @Autowired
    KafkaTemplate<String, Advertisement> kafkaTemplate;

    @SneakyThrows
    @Scheduled(cron = "*/30 * * * * *")
    public void TransferJsonArrayToKafka() {
        List<Advertisement> advertisements = extractorService.extract();
        System.out.println(LocalDateTime.now());
        if (!advertisements.isEmpty()) {
            System.out.println("advertisements = " + advertisements);
            advertisements.forEach(ad -> kafkaTemplate.send(KafkaConfiguration.TOPIC, ad));
        }
    }
}
