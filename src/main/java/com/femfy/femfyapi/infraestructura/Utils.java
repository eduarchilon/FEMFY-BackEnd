package com.femfy.femfyapi.infraestructura;

import com.femfy.femfyapi.domain.exception.ParseDateException;
import org.springframework.expression.ParseException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Utils {
    private Utils(){}

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

    public static Date addDate(Date date){
        LocalDate localDate = date.toLocalDate();
        LocalDate nuevaFecha = localDate.plusDays(1);
        return Date.valueOf(nuevaFecha);
    }

}
