package Calendar.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DateConvertorTest {

    private static DateConvertor convertor;

    @BeforeAll
    public static void setUp() {
        convertor = new DateConvertor();
    }

    @AfterAll
    public static void tearDown() {
        convertor = null;
    }

    @ParameterizedTest
    @MethodSource("getSource")
    public void testsArgumentsFromMethod(LocalDate date, String result) {
        assertThat(convertor.toRomanCalendar(date)).isEqualTo(result);
    }

    public static Stream<Arguments> getSource() {
        return Stream.of(
                Arguments.of(LocalDate.of(1989,1,20), "ante diem VII Idus Ianuarias MMDCCXLII AUC"),
                Arguments.of(LocalDate.of(1990,1,13), "pridie Kalendas Ianuarias MMDCCXLII AUC"),
                Arguments.of(LocalDate.of(2023,7,13), "pridie Kalendas Quintilis MMDCCLXXVI AUC"),
                Arguments.of(LocalDate.of(2024,3,12), "ante diem III Kalendas Martias MMDCCLXXVII AUC"),
                Arguments.of(LocalDate.of(2023,3,12), "ante diem III Kalendas Martias MMDCCLXXVI AUC"),
                Arguments.of(LocalDate.of(2023,2,27), "ante diem XVI Kalendas Martias MMDCCLXXVI AUC"),
                Arguments.of(LocalDate.of(2100,2,27), "ante diem XVII Kalendas Martias MMDCCCLIII AUC"),
                Arguments.of(LocalDate.of(2024,2,27), "ante diem XVII Kalendas Martias MMDCCLXXVII AUC"),
                Arguments.of(LocalDate.of(1899,1,20), "ante diem VI Idus Ianuarias MMDCLII AUC"),
                Arguments.of(LocalDate.of(1799,1,20), "ante diem V Idus Ianuarias MMDLII AUC"),
                Arguments.of(LocalDate.of(1699,1,20), "ante diem IV Idus Ianuarias MMCDLII AUC"),
                Arguments.of(LocalDate.of(2101,1,20), "ante diem VIII Idus Ianuarias MMDCCCLIV AUC"),
                Arguments.of(LocalDate.of(1900,3,13), "pridie Kalendas Martias MMDCLIII AUC"),
                Arguments.of(LocalDate.of(1900,3,12), "ante diem III Kalendas Martias MMDCLIII AUC")
        );
    }

}