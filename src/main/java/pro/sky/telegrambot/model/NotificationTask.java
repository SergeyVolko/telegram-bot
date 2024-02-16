package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notification_task")
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "text_notification")
    private String textNotification;

    @Column(name = "date")
    private LocalDateTime date;

    public NotificationTask() {

    }

    public NotificationTask(long chatId, String textNotification, LocalDateTime date) {
        this.chatId = chatId;
        this.textNotification = textNotification;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getTextNotification() {
        return textNotification;
    }

    public void setTextNotification(String textNotification) {
        this.textNotification = textNotification;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
