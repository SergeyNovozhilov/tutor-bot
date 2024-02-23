package ru.snovit.tutorbot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.snovit.tutorbot.entity.user.User;
import ru.snovit.tutorbot.repository.UserRepository;
import ru.snovit.tutorbot.service.handler.CallbackQueryHandler;
import ru.snovit.tutorbot.service.handler.CommandHandler;
import ru.snovit.tutorbot.service.handler.MessageHandler;
import ru.snovit.tutorbot.telegram.Bot;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

    CallbackQueryHandler callbackQueryHandler;
    CommandHandler commandHandler;
    MessageHandler messageHandler;
    UserRepository userRepository;

    public BotApiMethod<?> dispatch(Update update, Bot bot) {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.answer(update.getCallbackQuery(), bot);
        }

        if (update.hasMessage()) {
            var message = update.getMessage();
            userRepository.save(User.builder()
                            .chatId(message.getChatId())
                            .build());
            if (message.getText().charAt(0) == '/') {
                return commandHandler.answer(message, bot);
            }

            return messageHandler.answer(message, bot);
        }

        log.warn("Unsupported update type: {}", update);
        throw new UnsupportedOperationException("Unsupported update type: " + update);
    }
}
