package com.javaschool.telegrambot.controller;

import com.javaschool.telegrambot.MyApartmentBot;
import com.javaschool.telegrambot.models.Person;
import com.javaschool.telegrambot.models.Wrapper;
import com.javaschool.telegrambot.service.ReplyMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CianController {
    private final MyApartmentBot telegramBot;
    private ReplyMessageService replyMessageService;

    public CianController(MyApartmentBot telegramBot, ReplyMessageService replyMessageService) {
        this.telegramBot = telegramBot;
        this.replyMessageService = replyMessageService;
    }

    @PostMapping("/advertisement")
    public void returnAdvertisement(@RequestBody Wrapper wrap) {
        System.out.println(wrap.getAdvertisement().toString());
        telegramBot.brodCastMessage(wrap);
        return;
    }

    @PostMapping("/testperson")
    public void returnPerson(@RequestBody Person pers) throws JsonProcessingException {
        System.out.printf("TESST!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.printf(String.valueOf(user.getChatId()));
        ObjectMapper objectMapper = new ObjectMapper();
        telegramBot.sendMessage(pers.getChatId(),objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pers) );
        return;
    }
}
