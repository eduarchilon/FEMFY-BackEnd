package com.femfy.femfyapi.delivery.mapper;

import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;
import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.infraestructura.Utils;

public class CalendarEventMapper {

    public static CalendarEventDTO mapToDTO(CalendarEvent calendarEvent) {
        if (calendarEvent == null) {
            throw new EntityNotFoundException("Evento no encontrado");
        }

        CalendarEventDTO dto = new CalendarEventDTO();
        dto.setId(calendarEvent.getId());

        if (calendarEvent.getUser() != null) {
            dto.setUserId(calendarEvent.getUser().getId());
        }

        dto.setTitle(calendarEvent.getTitle());
        dto.setDateEvent(calendarEvent.getDateEvent() != null? calendarEvent.getDateEvent().toString() : null);
        dto.setHourAlert(calendarEvent.getHourAlert() != null? calendarEvent.getHourAlert().toString() : null);
        dto.setDescription(calendarEvent.getDescription());
        return dto;
    }

    public static CalendarEvent mapToEntity(CalendarEventDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setId(dto.getId());
        calendarEvent.setUser(user);
        calendarEvent.setTitle(dto.getTitle());
        calendarEvent.setDateEvent(Utils.parseDate(dto.getDateEvent()));
        calendarEvent.setHourAlert(Utils.parseHours(dto.getHourAlert()));
        calendarEvent.setDescription(dto.getDescription());
        return calendarEvent;
    }
}
