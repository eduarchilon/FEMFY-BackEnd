package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.controller.AssistantController;
import com.femfy.femfyapi.dto.ResponseDTO;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssistantControllerTest {

    @InjectMocks
    private AssistantController assistantController;

    @Mock
    private ChatgptService chatgptService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendSuccess() {
        // Arrange
        String message = "Hola asistente";
        String responseMessage = "Hola Julia, en qué te puedo ayudar?";
        when(chatgptService.sendMessage(message)).thenReturn(responseMessage);

        // Act
        ResponseDTO<String> response = assistantController.send(message);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(responseMessage, response.getData());
        verify(chatgptService, times(1)).sendMessage(message);
    }

    @Test
    public void testSendFail() {
        // Arrange
        String message = "Entrada inválida";
        String errorMessage = "Error - entrada inválida, por favor vuelva a intentarlo.";
        when(chatgptService.sendMessage(message)).thenReturn(errorMessage);

        // Act
        ResponseDTO<String> response = assistantController.send(message);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals("fail", response.getMessage());
        assertEquals(errorMessage, response.getData());
        verify(chatgptService, times(1)).sendMessage(message);
    }

}
