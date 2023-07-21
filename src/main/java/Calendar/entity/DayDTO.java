package Calendar.entity;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DayDTO {

    @NotNull(message = "⛔ Please fill the date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String inRoman;

    public DayDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInRoman() {
        return inRoman;
    }

    public void setInRoman(String inRoman) {
        this.inRoman = inRoman;
    }
}
