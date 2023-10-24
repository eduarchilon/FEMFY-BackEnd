package com.femfy.femfyapi.infraestructura.mapper;

import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;
import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;

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
        dto.setDateEvent(calendarEvent.getDateEvent());
        dto.setHourAlert(calendarEvent.getHourAlert());
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
        calendarEvent.setDateEvent(dto.getDateEvent());
        calendarEvent.setHourAlert(dto.getHourAlert());
        calendarEvent.setDescription(dto.getDescription());
        return calendarEvent;
    }
}
