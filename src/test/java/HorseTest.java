import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 5.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void shouldThrowIllegalArgumentExceptionWhenNameIsBlankOrWhitespace(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 5.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestHorse", -5.0, 10.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestHorse", 10.0, -5.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_shouldReturnCorrectName() {
        Horse horse = new Horse("TestHorse", 10.0, 5.0);
        assertEquals("TestHorse", horse.getName());
    }

    @Test
    void getHorseSpeedWhenHorseNameIsAnEvenLongerStringToTestTheLimitsOfTheNameFieldInTheHorseClassBecauseThePreviousNameWasNotLongEnoughAndIAmTooDumbToUnderstandWhatItTests() {
        Horse horse = new Horse("ThisIsAnExtremelyLongHorseNameToTestTheLimitsOfTheNameFieldInTheHorseClass", 10.0, 5.0);
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    void getDistance_shouldReturnCorrectDistance() {
        Horse horseWithDistance = new Horse("TestHorse", 10.0, 5.0);
        assertEquals(5.0, horseWithDistance.getDistance());

        Horse horseWithoutDistance = new Horse("TestHorse", 10.0);
        assertEquals(0.0, horseWithoutDistance.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    void move_shouldCallGetRandomDoubleAndCalculateDistanceCorrectly(double randomValue) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("TestHorse", 10.0, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(5.0 + 10.0 * randomValue, horse.getDistance());
        }
    }

}