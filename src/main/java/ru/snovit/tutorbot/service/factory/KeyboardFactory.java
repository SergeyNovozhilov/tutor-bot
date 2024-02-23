package ru.snovit.tutorbot.service.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardFactory {
    public InlineKeyboardMarkup getInlineKeyboard(
            List<String> text,
            List<Integer> configuration,
            List<String> data
    ){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var index = 0;

        for (Integer rowNum: configuration) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int i = 0; i < rowNum; i++) {
                var button = new InlineKeyboardButton();
                button.setText(text.get(index));
                button.setCallbackData(data.get(index));
                row.add(button);
                index++;
            }
            keyboard.add(row);
        }
        var keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup getReplyKeyboard(
            List<String> text,
            List<Integer> configuration
    ){
        List<KeyboardRow> keyboard = new ArrayList<>();
        var index = 0;

        for (Integer rowNum: configuration) {
            var row = new KeyboardRow();
            for (int i = 0; i < rowNum; i++) {
                var button = new KeyboardButton();
                button.setText(text.get(index));
                row.add(button);
                index++;
            }
            keyboard.add(row);
        }
        var keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
