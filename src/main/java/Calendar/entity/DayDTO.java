package Calendar.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DayDTO {

    private Integer id;

    @NotBlank(message = "Please fill the date")
    @NotNull(message = "Please fill the date")
    private LocalDate date;

    private String inRoman;
}
