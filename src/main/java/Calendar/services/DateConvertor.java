package Calendar.services;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class DateConvertor {

    private static final int AUC = 753;
    private static final int DAYS_TO_JULIAN = 13;

    public String toRomanCalendar(LocalDate date) {
        date = toJulian(date);
        List<Integer> earlierMonths = List.of(1, 2, 4, 6, 8, 9, 11, 12);
        if (earlierMonths.contains(date.getMonthValue())) {
            return calculate(date, 5, 13);
        }
        return calculate(date, 7, 15);
    }

    private LocalDate toJulian(LocalDate date) {
        return date.minusDays(DAYS_TO_JULIAN);
    }

    private String calculate(LocalDate date, int nony, int idy) {
        List<String> monthsInAblativ = List.of("Ianuariis",
                "Februariis", "Martiis", "Aprilibus", "Maiis", "Iuniae", "Quintilis", "Augustae",
                "Septembrīs", "Octobres", "Novembrīs", "Decembris");
        List<String> monthInAkuzativ = List.of("Ianuarias", "Februarias", "Martias", "Apriles",
                "Maias", "Iunias", "Quintilis", "Augustas", "Septembrīs", "Octobris", "Novembrīs",
                "Decembris");
        int day = date.getDayOfMonth();
        String result = "";
        if (day == 1) {
            result = "Kalendis " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else if (day < nony - 1) {
            result = "ante diem " + toRoman(nony - day + 1) + " Nonas " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == nony - 1) {
            result = "pridie Nonas " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == nony) {
            result = "Nonis " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else if (day < idy - 1) {
            result = "ante diem " + toRoman(idy - day + 1) + " Idus " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == idy - 1) {
            result = "pridie Idus " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == idy) {
            result = "Idibus " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else {
            int month = date.getMonthValue();
            if (date.getMonthValue() == 12) {month = 0;}
            result = calculateKalendas(date) + monthInAkuzativ.get(month);
        }
        return result + " " + yearAUC(date.getYear());
    }

    private String calculateKalendas(LocalDate date) {
        String result = "";
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        int dayOfMonth = month.length(date.isLeapYear());
        if (day < dayOfMonth) {
            result = "ante diem " + toRoman(dayOfMonth - day + 2) + " Kalendas ";
        } else {
            result = "pridie Kalendas ";
        }
        return result;
    }

    private String yearAUC(int year) {
        return toRoman(year + AUC) + " AUC";
    }

    private String toRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }
}
