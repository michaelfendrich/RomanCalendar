package Calendar.controllers;

import Calendar.entity.DayEntity;
import Calendar.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCalendarController {

    @Autowired
    private CalendarService service;

    @PostMapping("/api")
    public ResponseEntity<?> addItems(@RequestBody List<DayEntity> days) {
        return ResponseEntity.ok(service.saveAllDays(days));
    }
}
