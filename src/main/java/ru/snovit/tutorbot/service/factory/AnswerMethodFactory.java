package ru.snovit.tutorbot.service.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class AnswerMethodFactory {
    public SendMessage getSendMessage(Long chatId,
                                      String text,
                                      ReplyKeyboard keyboard,
                                      Boolean disablePreview) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup(keyboard)
                .disableWebPagePreview(disablePreview)
                .build();
    }

    public EditMessageText getEditMessage(CallbackQuery callbackQuery,
                                          String text,
                                          InlineKeyboardMarkup keyboard,
                                          Boolean disablePreview) {
        var message = (Message)callbackQuery.getMessage();
        return EditMessageText.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .text(text)
                .replyMarkup(keyboard)
                .disableWebPagePreview(disablePreview)
                .build();
    }
}
