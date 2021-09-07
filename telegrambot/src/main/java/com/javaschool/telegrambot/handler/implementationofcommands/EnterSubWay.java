package com.javaschool.telegrambot.handler.implementationofcommands;

import com.javaschool.telegrambot.api.BotCondition;
import com.javaschool.telegrambot.api.BotConditionContext;
import com.javaschool.telegrambot.handler.MessageHandler;
import com.javaschool.telegrambot.handler.annotation.BotCommand;
import com.javaschool.telegrambot.inmemory.UserInfo;
import com.javaschool.telegrambot.listcommands.Commands;
import com.javaschool.telegrambot.models.Criterion;
import com.javaschool.telegrambot.service.ReplyMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;

@BotCommand(command = Commands.SET_LOCATION)
public class EnterSubWay implements MessageHandler {
    private BotConditionContext userDataCache;
    private final ReplyMessageService replyMessageService;
    private  final UserInfo info;

    public EnterSubWay(BotConditionContext userDataCache, ReplyMessageService replyMessageService, UserInfo info) {
        this.userDataCache = userDataCache;
        this.replyMessageService = replyMessageService;
        this.info = info;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        int userId = Math.toIntExact(message.getFrom().getId());
        List<String> subways = Arrays.asList(message.getText().split(","));
        List<Criterion> crit = info.getUserCriterionData(userId);
        crit.get(crit.size() - 1).setSubway(subways);
        userDataCache.setCurrentBotConditionForUserWithId(userId, BotCondition.GET_FLATS);

        return replyMessageService.getTextMessage(chatId, "Список станций введен\nНачать поиск?");
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.SET_SUBWAY);
    }
}
