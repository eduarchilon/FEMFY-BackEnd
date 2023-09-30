package com.femfy.femfyapi.controller;


import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.entity.ResponseError;
import com.femfy.femfyapi.service.ICycleService;
import dto.CycleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cycle", produces = MediaType.APPLICATION_JSON_VALUE)
public class CycleController {

    @Autowired
    ICycleService iCycleService;

    @Operation(summary = "Registra el inicio del nuevo ciclo")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @PostMapping("/registerCycleStart")
    public ResponseEntity<?> registerCycleStart(@RequestBody Cycle cycle){
        try{
            return new ResponseEntity<>(iCycleService.registerCycleStart(cycle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Registra el fin del nuevo ciclo")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @PutMapping("/registerCycleEnd")
    public ResponseEntity<?> registerCycleEnd(@RequestBody Cycle cycle){
        try{
            return new ResponseEntity<>(iCycleService.registerCycleEnd(cycle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Devuelve el historial de ciclos de la usuario")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @GetMapping("/getCycleHistory/{idUser}")
    public ResponseEntity<List<CycleDTO>> getCycleHistory(@PathVariable(value = "idUser") Long idUser) {
        List<CycleDTO> list = null;
        try {
            list = iCycleService.getCycleHistory(idUser);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}