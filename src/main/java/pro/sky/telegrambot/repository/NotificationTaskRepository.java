package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    @Query(value = "SELECT * FROM notification_task WHERE date = ?1",
    nativeQuery = true)
    List<NotificationTask> getTaskInMinute(LocalDateTime dateTime);

    List<NotificationTask> findAllByDate(LocalDateTime dateTime);
}
