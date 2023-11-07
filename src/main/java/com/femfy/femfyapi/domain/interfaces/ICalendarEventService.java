package com.femfy.femfyapi.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.femfy.femfyapi.domain.entity.CalendarEvent;

public interface ICalendarEventService {

    List<CalendarEvent> getCalendarEvents();

    Optional<CalendarEvent> getCalendarEvent(Long id);

    List<CalendarEvent> getCalendarEventByUser(Long userId);

    CalendarEvent saveCalendarEvent(CalendarEvent calendarEvent);

    CalendarEvent updateCalendarEvent(CalendarEvent calendarEvent);

    void deleteCalendarEvent(Long id);
}