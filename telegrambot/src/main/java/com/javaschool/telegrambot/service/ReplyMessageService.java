package com.javaschool.telegrambot.service;


import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ReplyMessageService {
    public SendMessage getTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }

    public AnswerCallbackQuery getPopUpAnswer(String callbackId, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackId);
        answerCallbackQuery.setText(text);
        answerCallbackQuery.setShowAlert(false);
        return answerCallbackQuery;
    }

    public EditMessageText getEditedTextMessage(Long chatId, Integer messageId, String text) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setMessageId(messageId);
        editMessageText.setText(text);
        return editMessageText;
    }

    @SneakyThrows
    public SendPhoto getTextMessageWithImage(Long chatId, String text, String urlString) {
        try(InputStream in = new URL(urlString).openStream()){
            Files.copy(in, Paths.get("C:\\Users\\Nikita\\IdeaProjects\\rent-bot-moscow\\image.jpg"));
        }

        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(String.valueOf(chatId));
        sendPhotoRequest.setPhoto( new InputFile("C:\\Users\\Nikita\\IdeaProjects\\rent-bot-moscow\\image.jpg"));
        sendPhotoRequest.setCaption(text);

        return sendPhotoRequest;
    }

}
