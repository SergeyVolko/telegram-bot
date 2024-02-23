package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface BotService {
    SendMessage addTask(String taskStr, long chatId);

    List<NotificationTask> getTaskInMinute(LocalDateTime dateTime);
}
