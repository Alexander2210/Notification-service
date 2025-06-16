package com.example.notificationservice.mapper;

import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring", uses = NotificationPeriodMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserEntity toEntity(UserDTO dto);

    List<UserDTO> toDtos(List<UserEntity> users);

    UserDTO toDto(UserEntity user);
    void updateEntityFromDto(UserDTO dto, @MappingTarget UserEntity user);
}
