package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.delivery.controller.CalendarEventController;
import com.femfy.femfyapi.domain.entity.CalendarEvent;
import com.femfy.femfyapi.domain.interfaces.ICalendarEventService;
import com.femfy.femfyapi.delivery.dto.CalendarEventDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.femfy.femfyapi.delivery.mapper.CalendarEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Test
    void testGetEventsByUserSuccess() {
        givenUserHasEvents(userId, mockEvents());

        ResponseEntity<List<CalendarEventDTO>> response = whenGetEventsOf(userId);

        thenGetStatus(OK, response);

        thenGetEvents(response, mockEvents());

    }

    private void thenGetEvents(ResponseEntity<List<dto.CalendarEventDTO>> response, List<CalendarEventDTO> calendarEventDTOS) {
        assertAll(
                () -> assertEquals(mockEvents(), response.getBody())
        );
    }

    private void thenGetStatus(HttpStatus httpStatus, ResponseEntity<List<dto.CalendarEventDTO>> response) {
        assertAll(
                () -> assertEquals(OK, response.getStatusCode())
        );
    }

    private ResponseEntity<List<CalendarEventDTO>> whenGetEventsOf(Long userId) {
        return controller.getEventsByUser(userId);
    }

    private void givenUserHasEvents(Long userId, List<CalendarEventDTO> calendarEventDTOS) {
        when(service.getCalendarEventByUser(userId)).thenReturn(calendarEventDTOS);
    }

    private List<CalendarEventDTO> mockEvents() {
        List<CalendarEventDTO> mockEvents = new ArrayList<>();
        mockEvents.add(new CalendarEventDTO());
        return mockEvents;
    }

    @Test
    void testGetEventsByUserNoEvents() {
        Long userId = 1L;
        when(service.getCalendarEventByUser(userId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<CalendarEventDTO>> response = controller.getEventsByUser(userId);

        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetEventByIdSuccess() {
        Long eventId = 1L;
        CalendarEventDTO dto = new CalendarEventDTO();
        CalendarEvent mockEvent = new CalendarEvent();
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.of(mockEvent));

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
        );
    }

    @Test
    void testGetEventByIdNotFound() {
        Long eventId = 1L;
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.empty());

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllEvents() {
        List<CalendarEventDTO> mockEvents = new ArrayList<>();
        //when(service.getCalendarEvents()).thenReturn(mockEvents);

        ResponseEntity<List<CalendarEventDTO>> response = controller.getAllEvents();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockEvents, response.getBody())
        );
    }

    @Test
    void testCreateEvent() {
        CalendarEventDTO dto = new CalendarEventDTO();
        CalendarEvent event = new CalendarEvent();
        when(service.saveCalendarEvent(any(CalendarEvent.class))).thenReturn(event);

        ResponseEntity<CalendarEventDTO> response = controller.createEvent(CalendarEventMapper.mapToDTO(event));

       assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(dto, response.getBody())
       );
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
        Long eventId = 1L;
        doNothing().when(service).deleteCalendarEvent(eventId);

        ResponseEntity<Void> response = controller.deleteEvent(eventId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}