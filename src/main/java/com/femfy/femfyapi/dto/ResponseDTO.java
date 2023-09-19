package com.femfy.femfyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ResponseDTO<T> {
// <T> porque es una definición de genéricos
    private Integer code;
    private String message;
    private T data; //cuerpo de la respuesta

    public static <T> ResponseDTO<T> success(T data){
        return new ResponseDTO<>(200, "success", data);
    }

    public static <T> ResponseDTO<T> fail(T data){
        return new ResponseDTO<>(0, "fail", data);
    }

}
