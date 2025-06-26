package com.example.notificationservice.scheduler;

import com.example.notificationservice.dto.EventDTO;
import com.example.notificationservice.entity.UserEntity;
import com.example.notificationservice.handler.NotificationWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final NotificationWebSocketHandler webSocketHandler;

    public void scheduleNotification(UserEntity user, EventDTO event, LocalDateTime sendAt) {
        long delaySeconds = Duration.between(LocalDateTime.now(), sendAt).getSeconds();
        if (delaySeconds < 0) delaySeconds = 0;

        scheduler.schedule(() -> sendNotification(user, event), delaySeconds, TimeUnit.SECONDS);
    }

    private void sendNotification(UserEntity user, EventDTO event) {
        String logMessage = String.format("%s Пользователю %s отправлено оповещение с текстом: %s",
                event.getOccurredAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                user.getFullName(),
                event.getMessage());
        log.info(logMessage);
        webSocketHandler.sendNotification(logMessage);
    }
}
