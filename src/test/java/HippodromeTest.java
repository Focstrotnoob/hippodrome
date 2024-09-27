import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void horsesIsNullExcept(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void horsesIsNullExceptMessage(){
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void horsesIsEmptyExcept(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void horsesIsEmptyExceptMessage(){
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void sequenceHorse(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i, i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse:horses) {
            verify(horse).move();
        }
    }

    @Test
    public void validGetWinner(){
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse("horse1", 1, 1));
        horses.add(new Horse("horse2", 1, 2.2));
        horses.add(new Horse("horse3", 1, 2.5));
        horses.add(new Horse("horse4", 1, 4.5));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horses.get(3), hippodrome.getWinner());

    }



}
