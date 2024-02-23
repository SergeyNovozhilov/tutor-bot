package ru.snovit.tutorbot.service.manager;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Manager {
    BotApiMethod<?> answer(Message message);
    BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery);
}
