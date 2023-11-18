package com.femfy.femfyapi;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.expression.ParseException;

import com.femfy.femfyapi.exception.ParseDateException;

public class Utils {

	public static Date parseDate(String date) throws ParseDateException {
        Date dateSql = null;
        if(date != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                java.util.Date parsedDate = format.parse(date);
                dateSql = new Date(parsedDate.getTime());
            } catch (ParseException | java.text.ParseException e) {
                throw new ParseDateException("Error al parsear la fecha");
            }
        }
        return addDate(dateSql);
    }
    
    public static LocalTime parseHours(String hour) {
    	LocalTime init = null; 
    	if(hour != null){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hora = LocalTime.parse(hour, formato);
        	return hora;
        	}else {
        		return init;
        	}
    }

    public static Date addDate(Date date){
    	if (date!=null){
            LocalDate localDate = date.toLocalDate();
            LocalDate nuevaFecha = localDate.plusDays(1);
            return Date.valueOf(nuevaFecha);
    	}
    	return null;
    }
}
