package com.femfy.femfyapi.infraestructura.service;

import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.entity.User;
import com.femfy.femfyapi.domain.interfaces.ICalendarEventService;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.repository.CalendarEventRepository;
import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;
import com.femfy.femfyapi.infraestructura.mapper.CalendarEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService implements ICalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Override
    public List<CalendarEventDTO> getCalendarEvents() {
        List<CalendarEvent> calendarEventList = calendarEventRepository.findAll();
        return calendarEventList.stream()
                .map(CalendarEventMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CalendarEventDTO> getCalendarEvent(Long id) {
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        return calendarEvent.map(CalendarEventMapper::mapToDTO);
    }

    @Override
    public List<CalendarEventDTO> getCalendarEventByUser(Long userId) {
        List<CalendarEvent> calendarEvents = calendarEventRepository.findByUserId(userId);
        return calendarEvents.stream()
                .map(CalendarEventMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CalendarEventDTO saveCalendarEvent(CalendarEvent calendarEvent) {
        calendarEvent = calendarEventRepository.save(calendarEvent);
        return CalendarEventMapper.mapToDTO(calendarEvent);
    }

    @Override
    public CalendarEventDTO updateCalendarEvent(CalendarEvent calendarEvent) {
        Long idToUpdate = calendarEvent.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        CalendarEvent existingEvent = findCalendarEventById(idToUpdate);

        updateCalendarEventFields(existingEvent, calendarEvent);

        existingEvent = calendarEventRepository.save(existingEvent);

        return CalendarEventMapper.mapToDTO(existingEvent);
    }

    @Override
    public void deleteCalendarEvent(Long id) {
        calendarEventRepository.deleteById(id);
    }


    private CalendarEvent findCalendarEventById(Long eventId) {
        return calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un evento con el ID: " + eventId));
    }

    private void updateCalendarEventFields(CalendarEvent existingEvent, CalendarEvent calendarEvent) {
        if(calendarEvent.getTitle() != null){
            existingEvent.setTitle(calendarEvent.getTitle());
        }
        if(calendarEvent.getDateEvent() != null){
            existingEvent.setDateEvent(calendarEvent.getDateEvent());
        }
        if(calendarEvent.getHourAlert() != null){
            existingEvent.setHourAlert(calendarEvent.getHourAlert());
        }
        if(calendarEvent.getDescription() != null){
            existingEvent.setDescription(calendarEvent.getDescription());
        }
    }
}