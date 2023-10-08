package com.femfy.femfyapi.service;

import dto.CalendarEventDTO;

import java.util.List;
import java.util.Optional;

public interface ICalendarEventService {

    List<CalendarEventDTO> getCalendarEvents();

    Optional<CalendarEventDTO> getCalendarEvent(Long id);

    List<CalendarEventDTO> getCalendarEventByUser(Long user_id);

    CalendarEventDTO saveCalendarEvent(CalendarEventDTO calendarEventDTO);

    CalendarEventDTO updateCalendarEvent(CalendarEventDTO calendarEventDTO);

    void deleteCalendarEvent(Long id);
}
