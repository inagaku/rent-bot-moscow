package com.javaschool.telegrambot.handler.implementationofcommands;

import com.javaschool.telegrambot.api.BotCondition;
import com.javaschool.telegrambot.api.BotConditionContext;
import com.javaschool.telegrambot.handler.MessageHandler;
import com.javaschool.telegrambot.handler.annotation.BotCommand;
import com.javaschool.telegrambot.listcommands.Commands;
import com.javaschool.telegrambot.service.ReplyMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


@BotCommand(command = Commands.HELP)
public class HelpMessage implements MessageHandler {
    private final BotConditionContext userDataCache;
    private final ReplyMessageService replyMessageService;

    public HelpMessage(BotConditionContext userDataCache, ReplyMessageService replyMessageService) {
        this.userDataCache = userDataCache;
        this.replyMessageService = replyMessageService;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        int userId = Math.toIntExact(message.getFrom().getId());
        userDataCache.setCurrentBotConditionForUserWithId(userId,BotCondition.START);
        return replyMessageService.getTextMessage(chatId,
                String.join("\n\n",
                        "Справочная информация!" + "Для продолжения работы снова введите команду /start"
                ));
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.HELP);
    }
}
