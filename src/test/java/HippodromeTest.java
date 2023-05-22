import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void whenHorsesListIsNullConstructorTest() {
        Throwable e = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.",e.getMessage());
    }
    @Test
    void whenHorsesListIsBlankConstructorTest() {
        Throwable e = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",e.getMessage());
    }
    @Test
    void getHorsesTest() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse#" + i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }
    @Test
    void move() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockedHorse = Mockito.mock(Horse.class);
            horses.add(mockedHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(hippodrome.getHorses().get(i)).move();
        }
    }
    @Test
    void getWinner() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            horses.add(new Horse("horse#" + i, i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(99),hippodrome.getWinner());
    }
}