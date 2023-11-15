package com.femfy.femfyapi.delivery.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.delivery.dto.CycleDTO;
import com.femfy.femfyapi.delivery.mapper.CycleMapper;
import com.femfy.femfyapi.domain.entity.ResponseError;
import com.femfy.femfyapi.domain.interfaces.ICycleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/cycle", produces = MediaType.APPLICATION_JSON_VALUE)
public class CycleController {


    private final ICycleService cycleService;

    
    public CycleController(ICycleService cycleService){
        this.cycleService = cycleService;
    }

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
    public ResponseEntity<?> registerCycleStart(@RequestBody CycleDTO cycleDTO){
        try{
            CycleDTO dto = CycleMapper.mapToDTO(cycleService.registerCycleStart(CycleMapper.mapToEntity(cycleDTO)));
            return new ResponseEntity<>(dto, HttpStatus.OK);
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
    public ResponseEntity<?> registerCycleEnd(@RequestBody CycleDTO cycleDto){
        try{
            CycleDTO dto = CycleMapper.mapToDTO(cycleService.registerCycleEnd(CycleMapper.mapToEntity(cycleDto)));
            return new ResponseEntity<>(dto, HttpStatus.OK);
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
    public ResponseEntity<?> getCycleHistory(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<CycleDTO> list = cycleService.getCycleHistory(idUser).stream()
                                                                .map(CycleMapper::mapToDTO)
                                                                .collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Devuelve el ciclo de la usuario")
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
    @GetMapping("/getCycle/{idUser}")
    public ResponseEntity<?> getCycle(@PathVariable(value = "idUser") Long idUser, @RequestParam("dateBeging") String dateBeging ) {
        try {
            CycleDTO dto = CycleMapper.mapToDTO(cycleService.getCycleByIdUserAndDateBeging(idUser, dateBeging));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un ciclo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCycle(@PathVariable("id") Long id) {
        try {
            Map<String, String> res = cycleService.deleteCycle(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar un ciclo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateCycle")
    public ResponseEntity<?> updateCycle(@RequestBody CycleDTO cycleDto) {
        try {
            CycleDTO dto = CycleMapper.mapToDTO(cycleService.updateCycle(CycleMapper.mapToEntity(cycleDto)));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
