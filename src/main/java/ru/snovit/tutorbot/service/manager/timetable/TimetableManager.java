package ru.snovit.tutorbot.service.manager.timetable;

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
import static ru.snovit.tutorbot.service.data.CallbackQueryData.*;
import static ru.snovit.tutorbot.service.data.AnswerText.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimetableManager implements Manager {
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
            case TIMETABLE -> {
                return mainMenu(callbackQuery);
            }
            case TIMETABLE_SHOW -> {
                return show(callbackQuery);
            }
            case TIMETABLE_ADD -> {
                return add(callbackQuery);
            }
            case TIMETABLE_REMOVE -> {
                return remove(callbackQuery);
            }
            default -> throw new IllegalStateException("Unexpected value: " + callbackData);
        }
    }

    private BotApiMethod<?> show(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                """
                       📆 Выберете день недели
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(TIMETABLE)
                ),
                true
        );
    }

    private BotApiMethod<?> add(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                """
                        ✏️ Выберете день, в который хотите добавить занятие:
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(TIMETABLE)
                ),
                true
        );
    }

    private BotApiMethod<?> remove(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                """
                        ✂️ Выберете занятие, которое хотите удалить из вашего расписания
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(TIMETABLE)
                ),
                true
        );
    }

    private BotApiMethod<?> mainMenu(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                TIMETABLE_MAIN_MENU_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Расписание", "Добавить занятие", "Удалить занятие"),
                        List.of(1, 2),
                        List.of(TIMETABLE_SHOW, TIMETABLE_ADD, TIMETABLE_REMOVE)
                ),
                true
        );
    }

    private BotApiMethod<?> mainMenu(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessage(
                callbackQuery,
                TIMETABLE_MAIN_MENU_TEXT,
                keyboardFactory.getInlineKeyboard(
                        List.of("Расписание", "Добавить занятие", "Удалить занятие"),
                        List.of(1, 2),
                        List.of(TIMETABLE_SHOW, TIMETABLE_ADD, TIMETABLE_REMOVE)
                ),
                true
        );
    }
}
