package com.example.notificationservice.controller;

import com.example.notificationservice.dto.EventDTO;
import com.example.notificationservice.service.EventService;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO dto) {
        EventDTO saved = eventService.save(dto);
        notificationService.handleNewEvent(saved);
        return ResponseEntity.ok(saved);
    }
}
