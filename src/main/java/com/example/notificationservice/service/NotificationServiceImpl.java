package com.example.notificationservice.service;

import com.example.notificationservice.dto.EventDTO;
import com.example.notificationservice.entity.UserEntity;
import com.example.notificationservice.scheduler.NotificationScheduler;
import com.example.notificationservice.util.PeriodUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final NotificationScheduler notificationScheduler;

    @Override
    public void handleNewEvent(EventDTO eventDTO) {
        List<UserEntity> users = userService.findAllUsers();

        for (UserEntity user : users) {
            Optional<LocalDateTime> nearestTime = PeriodUtil.findNextValidTime(user.getNotificationPeriods(), eventDTO.getOccurredAt());
            nearestTime.ifPresent(time -> notificationScheduler.scheduleNotification(user, eventDTO, time));
        }
    }
}
