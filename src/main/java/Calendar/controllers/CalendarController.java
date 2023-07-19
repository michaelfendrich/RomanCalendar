package Calendar.controllers;

import Calendar.entity.DayDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalendarController {

    @GetMapping("/index")
    public String show(Model model) {
        model.addAttribute("day", new DayDTO());
        return "Ahoj";
    }
}
