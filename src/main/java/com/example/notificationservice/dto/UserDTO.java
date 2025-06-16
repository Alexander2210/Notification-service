package com.example.notificationservice.dto;

import com.example.notificationservice.util.PeriodUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String fullName;
    private List<NotificationPeriodDTO> notificationPeriods;

    @Override
    public String toString() {
        return PeriodUtil.format(notificationPeriods);
    }
}
