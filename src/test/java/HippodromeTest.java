import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenHippodromeConstructorIsCalledWithNullList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenHippodromeConstructorIsCalledWithEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_shouldReturnListWithSameObjectsInSameOrderAsConstructor() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse("Horse" + i, i + 1.0));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorses);

        List<Horse> actualHorses = hippodrome.getHorses();

        assertEquals(expectedHorses, actualHorses);
    }

    @Test
    void shouldInvokeMoveOnAllHorses() {
        List<Horse> horseMocks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = mock(Horse.class);
            horseMocks.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horseMocks);

        hippodrome.move();

        for (Horse horseMock : horseMocks) {
            verify(horseMock).move();
        }
    }

    @Test
    void shouldReturnHorseWithMaximumDistanceFromGetWinner() {
        Horse horse1 = new Horse("Horse1", 10.0, 100.0);
        Horse horse2 = new Horse("Horse2", 10.0, 150.0);
        Horse horse3 = new Horse("Horse3", 10.0, 120.0);
        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();

        assertEquals(horse2, winner);
    }

}