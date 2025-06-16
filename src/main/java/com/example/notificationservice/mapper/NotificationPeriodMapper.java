package com.example.notificationservice.mapper;

import com.example.notificationservice.dto.NotificationPeriodDTO;
import com.example.notificationservice.entity.NotificationPeriodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationPeriodMapper {
    @Mapping(target = "id", ignore = true)
    NotificationPeriodEntity toEntity(NotificationPeriodDTO dto);

    NotificationPeriodDTO toDto(NotificationPeriodEntity period);
}
