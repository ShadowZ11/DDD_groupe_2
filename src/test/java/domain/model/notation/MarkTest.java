package domain.model.notation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MarkTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, 19, 20})
    void shouldBeIrregular(int markValue) {
        Mark mark = new Mark(markValue);

        assertTrue(mark.isIrregular());
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 10, 14, 16})
    void shouldNotBeIrregular(int markValue) {
        Mark mark = new Mark(markValue);

        assertFalse(mark.isIrregular());
    }

    @Test
    void shouldHaveComment() {
        Mark mark = new Mark(10, "Good job");

        assertTrue(mark.hasComment());
    }

    @Test
    void shouldNotHaveComment() {
        Mark mark = new Mark(10);

        assertFalse(mark.hasComment());
    }
}