package Calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "days_in_roman_calendar")
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //format dd/mm
    @Column(name = "day_in_string")
    private String day;

    @Column(name = "in_roman")
    private String inRoman;

    public DayEntity() {}

    public DayEntity(Integer id, String day, String inRoman) {
        this.id = id;
        this.day = day;
        this.inRoman = inRoman;
    }

    public Integer getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getInRoman() {
        return inRoman;
    }

    public void setInRoman(String inRoman) {
        this.inRoman = inRoman;
    }
}
