package ru.snovit.tutorbot.service.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.snovit.tutorbot.service.manager.feedback.FeedbackManager;
import ru.snovit.tutorbot.service.manager.help.HelpManager;
import ru.snovit.tutorbot.service.manager.progresscontrol.ProgressControlManager;
import ru.snovit.tutorbot.service.manager.task.TaskManager;
import ru.snovit.tutorbot.service.manager.timetable.TimetableManager;
import ru.snovit.tutorbot.telegram.Bot;

import static ru.snovit.tutorbot.service.data.CallbackQueryData.*;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {
    FeedbackManager feedbackManager;
    HelpManager helpManager;
    TimetableManager timetableManager;
    TaskManager taskManager;
    ProgressControlManager progressControlManager;
    public BotApiMethod<?> answer(CallbackQuery callbackQuery, Bot bot) {
        var query = callbackQuery.getData().split("_")[0];
        switch (query) {
            case HELP -> {
                return helpManager.answerCallbackQuery(callbackQuery);
            }
            case FEEDBACK -> {
                return feedbackManager.answerCallbackQuery(callbackQuery);
            }
            case TIMETABLE -> {
                return timetableManager.answerCallbackQuery(callbackQuery);
            }
            case TASK -> {
                return taskManager.answerCallbackQuery(callbackQuery);
            }
            case PROGRESS -> {
                return progressControlManager.answerCallbackQuery(callbackQuery);
            }
            default -> throw new IllegalStateException("Unexpected value: " + query);
        }
    }
}
