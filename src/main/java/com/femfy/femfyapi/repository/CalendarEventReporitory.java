package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.CalendarEvent;

@Repository
public interface CalendarEventReporitory extends JpaRepository<CalendarEvent, Long> {

}
