package Calendar.entity.repository;

import Calendar.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<DayEntity, Integer> {

}
