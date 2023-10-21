package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;

import java.util.List;
import java.util.Optional;

public interface ICalendarEventService {

    List<CalendarEventDTO> getCalendarEvents();

    Optional<CalendarEventDTO> getCalendarEvent(Long id);

    List<CalendarEventDTO> getCalendarEventByUser(Long userId);

    CalendarEventDTO saveCalendarEvent(CalendarEventDTO calendarEventDTO);

    CalendarEventDTO updateCalendarEvent(CalendarEventDTO updatedDTO);

    void deleteCalendarEvent(Long id);
}