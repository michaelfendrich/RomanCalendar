package Calendar.services;

import Calendar.entity.DayEntity;

import java.util.List;

public interface CalendarService {

    List<DayEntity> saveAllDays(List<DayEntity> days);
}
