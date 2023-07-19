package Calendar.services.impl;

import Calendar.services.CalendarService;
import Calendar.entity.DayEntity;
import Calendar.entity.repository.CalendarRepository;
import Calendar.services.DateConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository repository;

    @Autowired
    private DateConvertor convertor;

    @Override
    public List<DayEntity> saveAllDays(List<DayEntity> days) {
        return repository.saveAll(days);
    }
}
