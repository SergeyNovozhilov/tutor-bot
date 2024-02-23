package ru.snovit.tutorbot.service.data;

public interface AnswerText {
    String START_TEXT =
            """
                        🖖Приветствую в Tutor-Bot, инструменте для упрощения взаимодействия репетитора и ученика.
                                               
                        Что умеет бот?
                        📌 Составлять расписание
                        📌 Прикреплять домашние задания
                        📌 Вести контроль успеваемости
                        """;
    String HELP_TEXT =
            """
                        📍 Доступные команды:
                        - /start
                        - /help
                        - /feedback
                        - /timetable
                        - /task
                        - /progress
                                               
                        📍 Доступные функции:
                        - Расписание
                        - Домашнее задание
                        - Контроль успеваемости
                        """;
    String FEEDBACK_TEXT =
            """
                    GitHub - https://github.com/SergeyNovozhilov
                    Telegram - https://t.me/SergeyNNovozhilov
                    """;
    String TIMETABLE_MAIN_MENU_TEXT =
            """
                    📆 Здесь вы можете управлять вашим расписанием
                     """;
    String TASK_MAIN_MENU_TEXT =
            """
                    🗂 Вы можете добавить домашнее задание вашему ученику
                    """;
    String PROGRESS_MAIN_MENU_TEXT =
            """
                    Прогресс
                    """;
}
