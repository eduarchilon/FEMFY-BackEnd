package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.ICalendarEventService;
import dto.CalendarEventDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

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
        CalendarEventDTO mockEvent = new CalendarEventDTO();
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.of(mockEvent));

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertAll(
                () -> assertEquals(OK, response.getStatusCode()),
                () -> assertEquals(mockEvent, response.getBody())
        );
    }

    @Test
    void testGetEventByIdNotFound() {
        Long eventId = 1L;
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.empty());

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllEvents() {
        List<CalendarEventDTO> mockEvents = new ArrayList<>();
        when(service.getCalendarEvents()).thenReturn(mockEvents);

        ResponseEntity<List<CalendarEventDTO>> response = controller.getAllEvents();

        assertAll(
                () -> assertEquals(OK, response.getStatusCode()),
                () -> assertEquals(mockEvents, response.getBody())
        );
    }

    @Test
    void testCreateEvent() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        when(service.saveCalendarEvent(any(CalendarEventDTO.class))).thenReturn(eventDTO);

        ResponseEntity<CalendarEventDTO> response = controller.createEvent(eventDTO);

        assertAll(
                () -> assertEquals(CREATED, response.getStatusCode()),
                () -> assertEquals(eventDTO, response.getBody())
        );
    }

    @Test
    void testUpdateEventSuccess() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        eventDTO.setId(1L);
        eventDTO.setUserId(2L);
        eventDTO.setTitle("Título");
        eventDTO.setDateEvent(new java.util.Date());
        eventDTO.setHourAlert(new java.util.Date());
        eventDTO.setDescription("Descripción");

        CalendarEventDTO updatedEventDTO = new CalendarEventDTO();

        when(service.updateCalendarEvent(eventDTO)).thenReturn(updatedEventDTO);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertAll(
                () -> assertEquals(OK, response.getStatusCode()),
                () -> assertEquals(updatedEventDTO, response.getBody())
        );

        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateEventNotFound() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        when(service.updateCalendarEvent(eventDTO)).thenReturn(null);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteEvent() {
        Long eventId = 1L;
        doNothing().when(service).deleteCalendarEvent(eventId);

        ResponseEntity<Void> response = controller.deleteEvent(eventId);

        assertEquals(NO_CONTENT, response.getStatusCode());
    }
}