package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.dto.ResponseDTO;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/assistant")
@RequiredArgsConstructor //inyeccion de dependencias
public class AssistantController {

    //viene de la libreria que agregamos en dependencias
    //libreria: https://github.com/flashvayne/chatgpt-spring-boot-starter
    //tutorial: https://www.youtube.com/watch?v=_DXUl4we6EQ
    private final ChatgptService chatgptService;

    @PostMapping("/send")
    public ResponseDTO<String> send(@RequestBody String message){
        log.info("message is: {}", message);
        String responseMessage = chatgptService.sendMessage(message);
        log.info("response is: {}", responseMessage);

        if (responseMessage.contains("error") || responseMessage.contains("Error")) {
            // Si el mensaje de respuesta contiene "error", devolver una respuesta de fallo
            return ResponseDTO.fail(responseMessage);
        } else {
            // En otros casos, devolver una respuesta de éxito
            return ResponseDTO.success(responseMessage);
        }
    }



}
