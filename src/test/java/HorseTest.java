import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class  HorseTest {

    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1 ,1 ));
    }

    @Test
    public void nullNameExMessage(){
        try {
            new Horse(null, 1 ,1 );
            fail();
        } catch (IllegalArgumentException e){
            String ex = e.getMessage();
            assertEquals("Name cannot be null.", ex);
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t","\n\n", ""})
    public void nameIsBlankException(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1 ,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t","\n\n", ""})
    public void nameIsBlankExceptionMessage(String name){
        try {
            new Horse(name, 1 ,1);
            fail();
        } catch (IllegalArgumentException e){
            String ex = e.getMessage();
            assertEquals("Name cannot be blank.", ex);
        }
    }

    @Test
    public void speedIsNotNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -2 ,1));
    }

    @Test
    public void speedIsNotNegativeExMessage(){
        try {
            new Horse("name", -3 ,1 );
            fail();
        } catch (IllegalArgumentException e){
            String ex = e.getMessage();
            assertEquals("Speed cannot be negative.", ex);
        }
    }

    @Test
    public void distanceIsNotNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2 ,-11));
    }

    @Test
    public void distanceIsNotNegativeExMessage(){
        try {
            new Horse("name", 3 ,-1 );
            fail();
        } catch (IllegalArgumentException e){
            String ex = e.getMessage();
            assertEquals("Distance cannot be negative.", ex);
        }
    }

    @Test
    public void validReturnGetNameMethod(){
        Horse horse = new Horse("HorseName", 20, 60);
        assertEquals(horse.getName(), "HorseName");
    }

    @Test
    public void validReturnSpeedGetMethod(){
        Horse horse = new Horse("HorseName", 20, 60);
        assertEquals(horse.getSpeed(), 20);
    }

    @Test
    public void validReturnGetDistanceMethod(){
        Horse horse = new Horse("HorseName", 20);
        assertEquals(horse.getDistance(), 0);
    }


    @Test
    public void validMove(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("HorseName", 20, 60).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2 , 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.4, 0.5, 1.0, 0.0})
    public void move(double random){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("horse", 4, 23);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(23 + 4 * random, horse.getDistance());
        }
    }




}
