package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import dto.CalendarEventDTO;
import com.femfy.femfyapi.entity.CalendarEvent;
import com.femfy.femfyapi.repository.CalendarEventReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService implements ICalendarEventService {

    private final CalendarEventReporitory calendarEventRepository;

    @Autowired
    public CalendarEventService(CalendarEventReporitory calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Override
    public List<CalendarEventDTO> getCalendarEvents() {
        List<CalendarEvent> calendarEventList = calendarEventRepository.findAll();
        return calendarEventList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CalendarEventDTO> getCalendarEvent(Long id) {
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        return calendarEvent.map(this::mapToDTO);
    }

    @Override
    public List<CalendarEventDTO> getCalendarEventByUser(Long userId) {
        List<CalendarEvent> calendarEvents = calendarEventRepository.findByUserId(userId);
        return calendarEvents.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CalendarEventDTO saveCalendarEvent(CalendarEventDTO calendarEventDTO) {
        CalendarEvent calendarEvent = mapToEntity(calendarEventDTO);
        calendarEvent = calendarEventRepository.save(calendarEvent);
        return mapToDTO(calendarEvent);
    }

    @Override
    public CalendarEventDTO updateCalendarEvent(CalendarEventDTO calendarEventDTO) {
        CalendarEvent existingEvent = findCalendarEventById(calendarEventDTO.getId());

        updateCalendarEventFields(existingEvent, calendarEventDTO);

        return mapToDTO(existingEvent);
    }

    @Override
    public void deleteCalendarEvent(Long id) {
        calendarEventRepository.deleteById(id);
    }

    private CalendarEventDTO mapToDTO(CalendarEvent calendarEvent) {
        CalendarEventDTO dto = new CalendarEventDTO();
        dto.setId(calendarEvent.getId());
        dto.setUserId(calendarEvent.getUser().getId());
        dto.setTitle(calendarEvent.getTitle());
        dto.setDateEvent(calendarEvent.getDateEvent());
        dto.setHourAlert(calendarEvent.getHourAlert());
        dto.setDescription(calendarEvent.getDescription());
        return dto;
    }

    private CalendarEvent mapToEntity(CalendarEventDTO dto) {
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

    private CalendarEvent findCalendarEventById(Long eventId) {
        return calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un evento con el ID: " + eventId));
    }

    private void updateCalendarEventFields(CalendarEvent existingEvent, CalendarEventDTO calendarEventDTO) {
        existingEvent.setTitle(calendarEventDTO.getTitle());
        existingEvent.setDateEvent(calendarEventDTO.getDateEvent());
        existingEvent.setHourAlert(calendarEventDTO.getHourAlert());
        existingEvent.setDescription(calendarEventDTO.getDescription());
    }

}
