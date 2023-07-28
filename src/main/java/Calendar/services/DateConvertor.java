package Calendar.services;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class DateConvertor {

    private static final int AB_URBE_CONDITA = 753;

    public String toRomanCalendar(LocalDate date) {
        date = toJulian(date);
        List<Integer> earlierMonths = List.of(1, 2, 4, 6, 8, 9, 11, 12);
        if (earlierMonths.contains(date.getMonthValue())) {
            return calculate(date, 5, 13);
        }
        return calculate(date, 7, 15);
    }

    //to Julian Calendar, minus 13 days, valid from 1900 to 2100
    private LocalDate toJulian(LocalDate date) {
        int daysToJulian = 13;
        if (date.isBefore(LocalDate.of(1700, 3, 11))) {
            daysToJulian = 10;
        } else if (date.isBefore(LocalDate.of(1800, 3, 12))) {
            daysToJulian = 11;
        } else if (date.isBefore(LocalDate.of(1900, 3, 13))) {
            daysToJulian = 12;
        } else if (date.isAfter(LocalDate.of(2200, 3, 14))) {
            daysToJulian = 15;
        } else if (date.isAfter(LocalDate.of(2100, 3, 13))) {
            daysToJulian = 14;
        }
        return date.minusDays(daysToJulian);
    }

    private String calculate(LocalDate date, int nony, int idy) {
        List<String> monthsInAblativ = List.of("Ianuariis",
                "Februariis", "Martiis", "Aprilibus", "Maiis", "Iuniae", "Quintilis", "Augustae",
                "Septembrīs", "Octobres", "Novembrīs", "Decembris");
        List<String> monthInAkuzativ = List.of("Ianuarias", "Februarias", "Martias", "Apriles",
                "Maias", "Iunias", "Quintilis", "Augustas", "Septembrīs", "Octobris", "Novembrīs",
                "Decembris");
        int day = date.getDayOfMonth();
        String result;
        if (day == 1) {
            result = "Kalendis " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else if (day < nony - 1) {
            result = "ante diem " + toRomanNumbers(nony - day + 1) + " Nonas " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == nony - 1) {
            result = "pridie Nonas " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == nony) {
            result = "Nonis " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else if (day < idy - 1) {
            result = "ante diem " + toRomanNumbers(idy - day + 1) + " Idus " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == idy - 1) {
            result = "pridie Idus " + monthInAkuzativ.get(date.getMonthValue() - 1);
        } else if (day == idy) {
            result = "Idibus " + monthsInAblativ.get(date.getMonthValue() - 1);
        } else {
            int month = date.getMonthValue();
            if (month == 12) {month = 0;}
            result = calculateToKalendas(date) + monthInAkuzativ.get(month);
        }
        return result + " " + yearAUC(date.getYear());
    }

    private String calculateToKalendas(LocalDate date) {
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        boolean isLeapYear = date.isLeapYear();
        //if the Year is divisible by 4 is always leap in Julian calendar
        if (date.getYear() % 4 == 0) {
            isLeapYear = true;
        }
        int dayOfMonth = month.length(isLeapYear);
        if (day < dayOfMonth) {
            return "ante diem " + toRomanNumbers(dayOfMonth - day + 2) + " Kalendas ";
        }
        return "pridie Kalendas ";
    }

    private String yearAUC(int year) {
        return toRomanNumbers(year + AB_URBE_CONDITA) + " AUC";
    }

    private String toRomanNumbers(int num) {
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
