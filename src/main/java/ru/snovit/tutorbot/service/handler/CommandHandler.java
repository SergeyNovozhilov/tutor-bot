package ru.snovit.tutorbot.service.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.snovit.tutorbot.service.manager.feedback.FeedbackManager;
import ru.snovit.tutorbot.service.manager.help.HelpManager;
import ru.snovit.tutorbot.service.manager.progresscontrol.ProgressControlManager;
import ru.snovit.tutorbot.service.manager.start.StartManager;
import ru.snovit.tutorbot.service.manager.task.TaskManager;
import ru.snovit.tutorbot.service.manager.timetable.TimetableManager;
import ru.snovit.tutorbot.telegram.Bot;

import static ru.snovit.tutorbot.service.data.Command.*;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {
    StartManager startManager;
    FeedbackManager feedbackManager;
    HelpManager helpManager;
    TimetableManager timetableManager;
    TaskManager taskManager;
    ProgressControlManager progressControlManager;
    public BotApiMethod<?> answer(Message message, Bot bot) {
        var command = message.getText();
        switch(command) {
            case START_COMMAND -> {
                return startManager.answer(message);
            }
            case FEEDBACK_COMMAND -> {
                return feedbackManager.answer(message);
            }
            case HELP_COMMAND -> {
                return helpManager.answer(message);
            }
            case TIMETABLE_COMMAND -> {
                return timetableManager.answer(message);
            }
            case TASK_COMMAND -> {
                return taskManager.answer(message);
            }
            case PROGRESS_COMMAND -> {
                return progressControlManager.answer(message);
            }
            default -> {
                return defaultAnswer(message);
            }
        }
    }

    private BotApiMethod<?> defaultAnswer(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(message.getText() + " - unsupported command")
                .build();
    }
}
