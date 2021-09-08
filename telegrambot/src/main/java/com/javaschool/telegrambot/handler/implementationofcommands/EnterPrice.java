package com.javaschool.telegrambot.handler.implementationofcommands;

import com.javaschool.telegrambot.api.BotCondition;
import com.javaschool.telegrambot.api.BotConditionContext;
import com.javaschool.telegrambot.handler.MessageHandler;
import com.javaschool.telegrambot.handler.annotation.BotCommand;
import com.javaschool.telegrambot.inmemory.UserInfo;
import com.javaschool.telegrambot.listcommands.Commands;
import com.javaschool.telegrambot.models.Person;
import com.javaschool.telegrambot.service.ReplyMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;

@BotCommand(command = Commands.SET_PRICE)
public class EnterPrice implements MessageHandler {
    @Autowired
    private BotConditionContext userDataCache;
    private final ReplyMessageService replyMessageService;
    private  final UserInfo info;

    public EnterPrice(ReplyMessageService replyMessageService, UserInfo info) {
        this.replyMessageService = replyMessageService;
        this.info = info;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        int userId = Math.toIntExact(message.getFrom().getId());
        List<String> twoDigits = Arrays.asList(message.getText().split("-"));
        if (twoDigits.size() == 1) {
            return replyMessageService.getTextMessage(chatId, "Введите два числа через -");
        }
        try {
            int end = Integer.parseInt(twoDigits.get(1));
            System.out.printf(String.valueOf(end));
            int start = Integer.parseInt(twoDigits.get(0));
            System.out.printf(String.valueOf(start));
            if(end < start){
                return replyMessageService.getTextMessage(chatId, "Неверный диапозон");
            } else {
                Person pers = new Person(userId, chatId);
                pers.getCrit().setEndValue(end);
                pers.getCrit().setStartValue(start);
                info.saveUserCriterionData(userId, pers.getCrit());
                userDataCache.setCurrentBotConditionForUserWithId(userId, BotCondition.SET_SUBWAY);
            }
        } catch (NumberFormatException e){
            return replyMessageService.getTextMessage(chatId, "Нужно подать два чилса разделенными -");
        }


//        return replyMessageService.getTextMessage(chatId, "Цена принята \nВведите нужные станции метро или поставте 0, если этот критерий не нужен:");
        return replyMessageService.getTextMessage(chatId, "Цена принята \nВведите нужные станции метро");
    }


    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.SET_PRICE);
    }
}
