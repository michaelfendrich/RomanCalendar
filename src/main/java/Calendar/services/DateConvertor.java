package Calendar.services;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateConvertor {

    private static final int AUC = 753;
    private static final int DAYS_TO_JULIAN = 13;

    public LocalDate toJulian(LocalDate date) {
        return date.minusDays(DAYS_TO_JULIAN);
    }

    public String yearAUC(int year) {
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
