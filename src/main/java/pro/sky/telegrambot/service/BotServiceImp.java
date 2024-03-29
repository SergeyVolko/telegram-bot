package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class BotServiceImp implements BotService {
    private static final Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final NotificationTaskRepository repository;


    public BotServiceImp(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public SendMessage addTask(String taskStr, long chatId) {
        Matcher matcher = PATTERN.matcher(taskStr);
        boolean result = matcher.matches();
        if (result) {
            String date = matcher.group(1);
            String item = matcher.group(3);
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            NotificationTask task = new NotificationTask(chatId, item, localDateTime);
            repository.save(task);
            return new SendMessage(chatId, "Задача добавлена успешно");
        }
        return new SendMessage(chatId, "Задача не добавлена");
    }

    @Override
    public List<NotificationTask> getTaskInMinute(LocalDateTime dateTime) {
        return repository.getTaskInMinute(dateTime);
    }
}
