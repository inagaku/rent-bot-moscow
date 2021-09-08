package com.javaschool.telegrambot;

import com.javaschool.telegrambot.models.Advertisement;
import com.javaschool.telegrambot.models.Wrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.javaschool.telegrambot.service.ReplyMessageService;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Component
public class MyApartmentBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    private final ProcessingUpdates updateReceiver;

    public MyApartmentBot(ProcessingUpdates updateReceiver) {
        this.updateReceiver = updateReceiver;
    }

    @Autowired
    ReplyMessageService replyMessageService;
    /* Перегружаем метод интерфейса LongPollingBot
    Теперь при получении сообщения наш бот будет отвечать сообщением Hi!
     */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        PartialBotApiMethod<? extends Serializable> responseToUser = updateReceiver.handleUpdate(update);
        if (responseToUser instanceof BotApiMethod) {
            try {
                execute(
                        (BotApiMethod<? extends Serializable>) responseToUser);
            } catch (TelegramApiException e) {
                log.error("Error occurred while sending message to user: {}", e.getMessage());
            }
        }
    }
    public SendMessage sendMessage(long chatId, String textMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }

    public void brodCastMessage(Wrapper wrap) {
        List<Long> chatsId = wrap.getChatIds();
        ObjectMapper objectMapper = new ObjectMapper();
        Advertisement add = wrap.getAdvertisement();

        String text = makePrettyString(add);
        for (Long chatId : chatsId) {
//            SendPhoto sendPhoto = replyMessageService.getTextMessageWithImage(chatId, text, add.getImages().split(",")[0]);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText(text);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String makePrettyString(Advertisement add) {
        String pretty = add.getUrl() + "\n"
                + add.getAddress() + "\n"
                + add.getArea() + "м2" + "\n"
                + "Комнат:" + add.getRoomsCount() + "\n\n"
                + add.getMetro() + "\n\n"
                + add.getDescription() + "\n\n"
                + add.getImages().split(",")[0];
        return pretty;
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
