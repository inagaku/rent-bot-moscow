package com.javaschool.telegrambot;

import com.javaschool.telegrambot.api.BotCondition;
import com.javaschool.telegrambot.api.BotConditionContext;
import com.javaschool.telegrambot.handler.ConditionHandler;
import com.javaschool.telegrambot.service.ReplyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.EnumSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessingUpdates {
    private final ConditionHandler botConditionHandler;

    private final BotConditionContext botConditionUserContext;

    private final ReplyMessageService replyMessageService;


    public PartialBotApiMethod<? extends Serializable> handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            BotCondition botCondition1 = getBotCondition1(message);

            log.info(
                    "Message from: {}; " +
                            "chat id: {};  " +
                            "text: {}; " +
                            "bot condition: {}",
                    message.getFrom().getUserName(),
                    message.getChatId(),
                    message.getText(),
                    botCondition1
            );

            return botConditionHandler.handleTextMessageByCondition(message, botCondition1);
        }
        else {
            log.error(
                    "Unsupported request from: {}; " +
                            "chatId: {}",
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getChatId()
            );

            return replyMessageService.getTextMessage(update.getMessage().getChatId(), "Я могу принимать только текстовые сообщения!");
        }
    }

    protected BotCondition getBotCondition1(Message message) {
        Integer userId = Math.toIntExact(message.getFrom().getId());
        String text = message.getText();
        BotCondition botCondition = EnumSet.allOf(BotCondition.class)
                .stream()
                .filter(e -> e.toString().equals(text))
                .findFirst()
                .orElse(botConditionUserContext.getCurrentBotConditionForUserById(userId));
        botConditionUserContext.setCurrentBotConditionForUserWithId(userId, botCondition);
        System.out.printf("Отработка метода getBotCondition1 = " + String.valueOf(botCondition));
        return botCondition;
    }

}
