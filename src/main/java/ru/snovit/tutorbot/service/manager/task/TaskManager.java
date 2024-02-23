package ru.snovit.tutorbot.service.manager.task;

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

import static ru.snovit.tutorbot.service.data.AnswerText.TASK_MAIN_MENU_TEXT;
import static ru.snovit.tutorbot.service.data.AnswerText.TIMETABLE_MAIN_MENU_TEXT;
import static ru.snovit.tutorbot.service.data.CallbackQueryData.*;
import static ru.snovit.tutorbot.service.data.CallbackQueryData.TIMETABLE_REMOVE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskManager implements Manager {
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
            case TASK -> {
                return mainMenu(callbackQuery);
            }
            case TASK_CREATE -> {
                return create(callbackQuery);
            }
            default -> throw new IllegalStateException("Unexpected value: " + callbackData);
        }
    }

    private BotApiMethod<?> create(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                """
                        👤 Выберете ученика, которому хотите дать домашнее задание
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(TASK)
                ),
                true
        );
    }

    private BotApiMethod<?> mainMenu(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                TASK_MAIN_MENU_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Прикрепить домашнее задание"),
                        List.of(1),
                        List.of(TIMETABLE_SHOW)
                ),
                true
        );
    }

    private BotApiMethod<?> mainMenu(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                TASK_MAIN_MENU_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Прикрепить домашнее задание"),
                        List.of(1),
                        List.of(TIMETABLE_SHOW)
                ),
                true
        );
    }
}
