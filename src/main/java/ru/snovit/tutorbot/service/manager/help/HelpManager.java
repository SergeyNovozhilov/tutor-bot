package ru.snovit.tutorbot.service.manager.help;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.snovit.tutorbot.service.factory.AnswerMethodFactory;
import ru.snovit.tutorbot.service.manager.Manager;

import static ru.snovit.tutorbot.service.data.AnswerText.HELP_TEXT;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HelpManager implements Manager {
    AnswerMethodFactory answerMethodFactory;
    @Override
    public BotApiMethod<?> answer(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                HELP_TEXT,
                null,
                true
        );
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                HELP_TEXT,
                null,
                true
        );
    }
}
