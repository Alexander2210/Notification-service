package com.example.notificationservice.mapper;

import com.example.notificationservice.dto.EventDTO;
import com.example.notificationservice.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    EventEntity toEntity(EventDTO dto);

    EventDTO toDto(EventEntity event);
}
