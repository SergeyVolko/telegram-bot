package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.BotService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private boolean isStart = false;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private BotService service;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            long chatId = update.message().chat().id();
            String taskMessage = update.message().text();
            if ("/start".equals(taskMessage)) {
                String message = "Введите задачу в формате <01.01.2022 20:00 Сделать домашнюю работу>";
                SendMessage sendMessage = new SendMessage(chatId, message);
                telegramBot.execute(sendMessage);
                isStart = true;
                return;
            }
            if (!isStart) {
                String message = "Бот еще не стартовал.";
                SendMessage sendMessage = new SendMessage(chatId, message);
                telegramBot.execute(sendMessage);
                return;
            }
            if (service.addTask(taskMessage, chatId)) {
                telegramBot.execute(new SendMessage(chatId, "Задача успешно добавлена."));
            } else {
                telegramBot.execute(new SendMessage(chatId, "Ошибка формата ввода."));
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
