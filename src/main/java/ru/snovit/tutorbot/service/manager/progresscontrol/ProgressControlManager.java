package ru.snovit.tutorbot.service.manager.progresscontrol;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.snovit.tutorbot.service.factory.AnswerMethodFactory;
import ru.snovit.tutorbot.service.factory.KeyboardFactory;
import ru.snovit.tutorbot.service.manager.Manager;

import java.util.List;

import static ru.snovit.tutorbot.service.data.AnswerText.PROGRESS_MAIN_MENU_TEXT;
import static ru.snovit.tutorbot.service.data.AnswerText.TASK_MAIN_MENU_TEXT;
import static ru.snovit.tutorbot.service.data.CallbackQueryData.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgressControlManager implements Manager {
    AnswerMethodFactory answerMethodFactory;
    KeyboardFactory keyboardFactory;
    @Override
    public BotApiMethod<?> answer(Message message) {
        return mainMenu(message);
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery) {
        var callbackData = callbackQuery.getData();
        switch (callbackData) {
            case PROGRESS -> {
                return mainMenu(callbackQuery);
            }
            case PROGRESS_STAT -> {
                return stat(callbackQuery);
            }
            default -> throw new IllegalStateException("Unexpected value: " + callbackData);
        }
    }

    private BotApiMethod<?> mainMenu(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                """
                        Здесь будет статистика
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Стстистика успеваимости."),
                        List.of(1),
                        List.of(PROGRESS_STAT)
                ),
                true
        );
    }

    private BotApiMethod<?> mainMenu(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                PROGRESS_MAIN_MENU_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Статистика успеваемости"),
                        List.of(1),
                        List.of(PROGRESS_STAT)
                ),
                true
        );
    }

    private BotApiMethod<?> stat(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                """
                      Здесь вы можете увидеть
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(PROGRESS)
                ),
                true
        );
    }
}
