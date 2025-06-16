package com.example.notificationservice.service;

import com.example.notificationservice.dto.EventDTO;

public interface NotificationService {
    void handleNewEvent(EventDTO eventDTO);
}
