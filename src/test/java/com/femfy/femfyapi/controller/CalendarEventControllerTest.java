package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.femfy.femfyapi.delivery.controller.CalendarEventController;
import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;
import com.femfy.femfyapi.delivery.mapper.CalendarEventMapper;
import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.interfaces.ICalendarEventService;


class CalendarEventControllerTest {

    @InjectMocks
    private CalendarEventController controller;

    @Mock
    private ICalendarEventService service;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }
    private Long userId = 1L;
    private Long eventId = 1L;
    private List<CalendarEventDTO> mockEvents = new ArrayList<>();
    
    @Test
    void testGetEventsByUserSuccess() {
        givenUserHasEvents(userId, mockEvents());
        ResponseEntity<List<CalendarEventDTO>> response = whenGetEventsOf(userId);
        thenGetStatus(OK, response);
        thenGetEvents(response, mockEvents());
    }

    @Test
    void testGetEventsByUserNoEvents() {
        when(service.getCalendarEventByUser(userId)).thenReturn(new ArrayList<>());
        ResponseEntity<List<CalendarEventDTO>> response = whenGetEventsOf(userId);
        thenGetStatusError(NOT_FOUND, response);
    }

    @Test
    void testGetEventByIdSuccess() {
        CalendarEvent mockEvent = new CalendarEvent();
        givenEventHasIdEvent(eventId, mockEvent);
        ResponseEntity<CalendarEventDTO> response = whenGetEventOf(eventId);
        thenGetEventOK(OK,response);
    }

    @Test
    void testGetEventByIdNotFound() {
    	givenEventHasIdEventEmpty(eventId);
        ResponseEntity<CalendarEventDTO> response = whenGetEventOf(eventId);
        thenGetEventError(NOT_FOUND, response);
    }

    @Test
    void testGetAllEvents() {
    	List<CalendarEvent> calendarEvents = new ArrayList<>();
    	givenEventsAll(calendarEvents);
        ResponseEntity<List<CalendarEventDTO>> response = whenGetAllEventOf();
        thenGetAllEventOK(OK, response);
    }

    @Test
    void testCreateEvent() {
        givenCreateEvent();
        ResponseEntity<CalendarEventDTO> response = whenCreateEvent();
        thenGetEventOK(HttpStatus.CREATED,response);
    }

    @Test
    void testUpdateEventSuccess() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1L);
        event.setTitle("test");
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        eventDTO.setTitle("test");
        eventDTO.setId(1L);
        when(service.updateCalendarEvent(any(CalendarEvent.class))).thenReturn(event);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(eventDTO, response.getBody())
        );
    }

    @Test
    void testUpdateEventNotFound() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        CalendarEvent event = new CalendarEvent();
        when(service.updateCalendarEvent(event)).thenReturn(null);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(service).deleteCalendarEvent(eventId);

        ResponseEntity<Void> response = controller.deleteEvent(eventId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
//metodsbyUser
    private void givenUserHasEvents(Long userId, List<CalendarEvent> calendarEvent) {
        when(service.getCalendarEventByUser(userId)).thenReturn(calendarEvent);
    }
    
    private ResponseEntity<List<CalendarEventDTO>> whenGetEventsOf(Long userId) {
    	return controller.getEventsByUser(userId);
    }
    
    private void thenGetStatus(HttpStatus httpStatus, ResponseEntity<List<CalendarEventDTO>> response) {
        assertAll(() -> assertEquals(OK, response.getStatusCode()));
    }

    private void thenGetEvents(ResponseEntity<List<CalendarEventDTO>> response, List<CalendarEvent> calendarEvent) {
    	
        assertAll(() -> assertEquals(mockEvents().stream().map(CalendarEventMapper::mapToDTO).collect(Collectors.toList()), response.getBody()));
    }
    
    private void thenGetStatusError(HttpStatus httpStatus, ResponseEntity<List<CalendarEventDTO>> response) {
        assertAll(() -> assertEquals(NOT_FOUND, response.getStatusCode()));
    }

    private List<CalendarEvent> mockEvents() {
        List<CalendarEvent> mockEvents = new ArrayList<>();
        mockEvents.add(new CalendarEvent());
        return mockEvents;
    }

  //metodsbyEvent    
    private ResponseEntity<CalendarEventDTO> whenGetEventOf(Long eventId) {
    	return controller.getEventById(eventId);
    }
    
    private void givenEventHasIdEvent(Long idEvent, CalendarEvent calendarEvent) {
        when(service.getCalendarEvent(idEvent)).thenReturn(Optional.of(calendarEvent));
    }
   
    private void givenEventHasIdEventEmpty(Long idEvent) {
    	 when(service.getCalendarEvent(idEvent)).thenReturn(Optional.empty());
    }
   
    private void thenGetEventOK(HttpStatus httpStatus, ResponseEntity<CalendarEventDTO> response) {
    	CalendarEventDTO dto = new CalendarEventDTO();
        assertAll(
                () -> assertEquals(httpStatus, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
        );
    }
    
    private void thenGetEventError(HttpStatus httpStatus, ResponseEntity<CalendarEventDTO> response) {
    	assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    private ResponseEntity<List<CalendarEventDTO>> whenGetAllEventOf() {
    	return controller.getAllEvents();
    }
    
    private void thenGetAllEventOK(HttpStatus httpStatus, ResponseEntity<List<CalendarEventDTO>> response) {
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockEvents, response.getBody())
        );
    }
    
    private void givenEventsAll(List<CalendarEvent> events) {
   	 when(service.getCalendarEvents()).thenReturn(events);
   }
    
    private void givenCreateEvent() {
    	CalendarEvent event = new CalendarEvent();
    	when(service.saveCalendarEvent(any(CalendarEvent.class))).thenReturn(event);
    }
    
    private ResponseEntity<CalendarEventDTO> whenCreateEvent() {
    	CalendarEvent event = new CalendarEvent();
    	return controller.createEvent(CalendarEventMapper.mapToDTO(event));
    }
    
}