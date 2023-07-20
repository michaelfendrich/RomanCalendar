package Calendar.controllers;

import Calendar.controllers.entity.DayDTO;
import Calendar.services.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService service;

    @GetMapping("/calendar")
    public String show(Model model) {
        model.addAttribute("day", new DayDTO());
        return "index.html";
    }

    @PostMapping("/calendar")
    public String getRomanCalendar(@ModelAttribute("day") @Valid DayDTO dayDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index.html";
        }
        service.convertToRoman(dayDTO);
        return "index.html";
    }
}
