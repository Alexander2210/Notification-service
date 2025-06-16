package com.example.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NotificationPeriodDTO {
    private Long id;
    private String dayOfWeekStart;
    private String timeStart;
    private String timeEnd;
}
