package com.javaschool.telegrambot.handler.implementationofcommands;

import com.javaschool.telegrambot.api.BotCondition;
import com.javaschool.telegrambot.api.BotConditionContext;
import com.javaschool.telegrambot.emoji.Emoji;
import com.javaschool.telegrambot.handler.MessageHandler;
import com.javaschool.telegrambot.handler.annotation.BotCommand;
import com.javaschool.telegrambot.handler.buttons.ReplyKeyboardMarkupBuilder;
import com.javaschool.telegrambot.listcommands.Commands;
import com.javaschool.telegrambot.service.ReplyMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@BotCommand(command = Commands.START)
public class StartMessage implements MessageHandler {

    private BotConditionContext userDataCache;

    private final ReplyMessageService replyMessageService;
    private final List<MessageHandler> handlers;

    public StartMessage(BotConditionContext userDataCache, ReplyMessageService replyMessageService, List<MessageHandler> handlers) {
        this.userDataCache = userDataCache;

        this.replyMessageService = replyMessageService;
        this.handlers = handlers;
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.START);
    }

    @Override
    public SendMessage handle(Message message) {
        int userId = Math.toIntExact(message.getFrom().getId());
        return message.getText().equals("/start")
                ? getMainMenu(userId, message.getChatId())
                : replyMessageService.getTextMessage(message.getChatId(), "Такой команды я не знаю " + Emoji.EYES);
    }

    private SendMessage getMainMenu(int userId, Long chatId) {
        userDataCache.setCurrentBotConditionForUserWithId(userId, BotCondition.SET_PRICE);
        ReplyKeyboardMarkupBuilder builder = ReplyKeyboardMarkupBuilder.create(chatId);
        builder.setText("Добро пожаловать! "
                + "\n\nДля просмотра квартиры введите нужную вам цену(пример ввода 3456-789)");
        for (MessageHandler handler : handlers) {
            BotCommand annotation = handler.getClass().getAnnotation(BotCommand.class);
            String description = annotation.command()[0].getDescription();
            if (!description.isEmpty()) {
                builder.row()
                        .button(description)
                        .endRow();
            }
        }
        return builder.build();
        /*return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Добро пожаловать! "
                        + "\n\nЧтобы воспользоваться моим функционалом, нажмите нужную кнопку на появившейся клавиатуре. "
                        + Emoji.MENU)
                .row()
                .button("Введите цену квартиры(формат start-end)")
                .endRow()
                .row()
                .button("Введите метро")
                .endRow()
                .row()
                .button("Поиск квартир")
                .row()
                .button("Помощь")
                .endRow()
                .build();*/
    }

}
