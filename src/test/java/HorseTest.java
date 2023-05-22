import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {
    Horse horseWithThreeArgs = new Horse("Жучка", 100500, 3500);
    @Mock
    Horse mockedHorse;

    @Test
    void whenNameIsNullConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null,3));
        assertEquals("Name cannot be null.",exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "", "\n", "\r", "\f"})
    void whenNameIsBlankConstructorTest(String s) {
        System.out.println(s.isBlank());
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(s,3));
        assertEquals("Name cannot be blank.",exception.getMessage());
    }
    @Test
    void whenSpeedIsNegativeConstructorTest() {
        Throwable e = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Зоя", -1));
        assertEquals("Speed cannot be negative.",e.getMessage());
    }
    @Test
    void whenDistanceIsNegativeConstructorTest() {
        Throwable e = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Борис", 1, -100));
        assertEquals("Distance cannot be negative.",e.getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Жучка",horseWithThreeArgs.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(100500,horseWithThreeArgs.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horseWithTwoArgs = new Horse("Игого", 15);
        assertEquals(0,horseWithTwoArgs.getDistance());
        assertEquals(3500,horseWithThreeArgs.getDistance());
    }

    @Test
    void callVerificationMoveTest() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            System.out.println("d1: " + horseWithThreeArgs.getDistance());
            mockedHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.2);
            horseWithThreeArgs.move();
            mockedHorse.verify(() ->Horse.getRandomDouble(0.2,0.9));
            System.out.println("d2: " + horseWithThreeArgs.getDistance());
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void correctValueMoveTest(double d) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            mockedHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(d);
            double startDistance = horseWithThreeArgs.getDistance();
            horseWithThreeArgs.move();
            assertEquals(horseWithThreeArgs.getDistance(),startDistance + horseWithThreeArgs.getSpeed() * Horse.getRandomDouble(0.2,0.9));
        }
    }
}