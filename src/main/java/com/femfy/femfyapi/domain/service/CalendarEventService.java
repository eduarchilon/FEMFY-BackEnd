package com.femfy.femfyapi.domain.service;

import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.exception.EntityNotFoundException;
import com.femfy.femfyapi.domain.interfaces.ICalendarEventService;
import com.femfy.femfyapi.domain.repository.CalendarEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService implements ICalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Override
    public List<CalendarEvent> getCalendarEvents() {
        return calendarEventRepository.findAll();
    }

    @Override
    public Optional<CalendarEvent> getCalendarEvent(Long id) {
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        return calendarEvent;
    }

    @Override
    public List<CalendarEvent> getCalendarEventByUser(Long userId) {
        return calendarEventRepository.findByUserId(userId);
    }

    @Override
    public CalendarEvent saveCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    @Override
    public CalendarEvent updateCalendarEvent(CalendarEvent calendarEvent) {
        Long idToUpdate = calendarEvent.getId();
        if (idToUpdate == null) {
            throw new EntityNotFoundException("El ID no puede ser nulo para la actualización");
        }

        CalendarEvent existingEvent = findCalendarEventById(idToUpdate);

        updateCalendarEventFields(existingEvent, calendarEvent);

        existingEvent = calendarEventRepository.save(existingEvent);

        return existingEvent;
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