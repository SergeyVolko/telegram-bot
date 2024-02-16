package pro.sky.telegrambot.schedule;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.service.BotService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ScheduledProcess {
    private final TelegramBot telegramBot;
    private final BotService service;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public ScheduledProcess(TelegramBot telegramBot, BotService service) {
        this.telegramBot = telegramBot;
        this.service = service;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        logger.info("Метод run() по рассписанию выполнился");
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        service.getTaskInMinute(dateTime).forEach(task ->
                telegramBot.execute(new SendMessage(task.getChatId(),
                        String.format("%s %s", task.getDate(), task.getTextNotification())))
        );
    }
}
