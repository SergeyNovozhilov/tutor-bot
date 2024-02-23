package ru.snovit.tutorbot.service.manager.start;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.snovit.tutorbot.service.factory.AnswerMethodFactory;
import ru.snovit.tutorbot.service.factory.KeyboardFactory;
import ru.snovit.tutorbot.service.manager.Manager;

import static ru.snovit.tutorbot.service.data.AnswerText.START_TEXT;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartManager implements Manager {
    KeyboardFactory keyboardFactory;
    AnswerMethodFactory answerMethodFactory;
    @Override
    public BotApiMethod<?> answer(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                START_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Помощь", "Обратная связь"),
                        List.of(2),
                        List.of("help", "feedback")),
                        true
        );
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
