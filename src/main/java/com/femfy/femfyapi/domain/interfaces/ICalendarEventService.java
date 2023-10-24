package com.femfy.femfyapi.domain.interfaces;

import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;
import com.femfy.femfyapi.domain.entity.CalendarEvent;

import java.util.List;
import java.util.Optional;

public interface ICalendarEventService {

    List<CalendarEventDTO> getCalendarEvents();

    Optional<CalendarEventDTO> getCalendarEvent(Long id);

    List<CalendarEventDTO> getCalendarEventByUser(Long userId);

    CalendarEventDTO saveCalendarEvent(CalendarEvent calendarEvent);

    CalendarEventDTO updateCalendarEvent(CalendarEvent calendarEvent);

    void deleteCalendarEvent(Long id);
}