package Calendar.services.impl;

import Calendar.entity.DayDTO;
import Calendar.services.CalendarService;
import Calendar.services.DateConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private DateConvertor convertor;

    @Override
    public void convertToRoman(DayDTO dayDTO) {
        dayDTO.setInRoman(convertor.toRomanCalendar(dayDTO.getDate()));
    }
}
