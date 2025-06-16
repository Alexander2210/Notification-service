package com.example.notificationservice.service;

import com.example.notificationservice.dto.EventDTO;
import com.example.notificationservice.mapper.EventMapper;
import com.example.notificationservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventDTO save(EventDTO dto) {
        return eventMapper.toDto(eventRepository.save(eventMapper.toEntity(dto)));
    }
}
