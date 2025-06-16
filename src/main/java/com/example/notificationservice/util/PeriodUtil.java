package com.example.notificationservice.util;

import com.example.notificationservice.dto.NotificationPeriodDTO;
import com.example.notificationservice.entity.NotificationPeriodEntity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class PeriodUtil {

    public static Optional<LocalDateTime> findNextValidTime(List<NotificationPeriodEntity> periods, LocalDateTime from) {
        LocalDateTime candidate = null;

        for (int daysAhead = 0; daysAhead < 7; daysAhead++) {
            LocalDateTime checkDate = from.plusDays(daysAhead);

            for (NotificationPeriodEntity period : periods) {
                if (period.getDayOfWeekStart() == checkDate.getDayOfWeek()) {
                    LocalDateTime start = LocalDateTime.of(checkDate.toLocalDate(), period.getTimeStart());
                    LocalDateTime end = LocalDateTime.of(checkDate.toLocalDate(), period.getTimeEnd());

                    if (!from.isAfter(end)) {
                        if (candidate == null || start.isBefore(candidate)) {
                            candidate = start;
                        }
                    }
                }
            }
        }

        return Optional.ofNullable(candidate);
    }

    public static String format(List<NotificationPeriodDTO> periods) {
        if (periods == null || periods.isEmpty()) return "";

        return periods.stream()
                .map(period -> {
                    DayOfWeek day = DayOfWeek.valueOf(period.getDayOfWeekStart());
                    String dayRu = day.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"));
                    return String.format("%s с %s до %s",
                            capitalize(dayRu),
                            period.getTimeStart(),
                            period.getTimeEnd()
                    );
                })
                .collect(Collectors.joining(", "));
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
