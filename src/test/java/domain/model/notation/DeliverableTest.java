package domain.model.notation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeliverableTest {
    @Test
    void shouldBeLate() {
        Deliverable deliverable = new Deliverable(
                LocalDate.of(2021, 2, 21)
        );

        assertTrue(deliverable.isLate(LocalDate.of(2021, 2, 20)));
    }

    @Test
    void shouldNotBeLate() {
        Deliverable deliverable = new Deliverable(
                LocalDate.of(2021, 2, 20)
        );

        assertFalse(deliverable.isLate(LocalDate.of(2021, 2, 21)));
    }

    @Test
    void shouldBeTooLate() {
        Deliverable deliverable = new Deliverable(
                LocalDate.of(2021, 2, 20)
        );

        assertTrue(deliverable.isTooLate(LocalDate.of(2021, 2, 10)));
    }

    @Test
    void shouldNotBeTooLate() {
        Deliverable deliverable = new Deliverable(
                LocalDate.of(2021, 2, 20)
        );

        assertFalse(deliverable.isTooLate(LocalDate.of(2021, 2, 15)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7})
    void shouldBeDaysLate(int daysLate) {
        Deliverable deliverable = new Deliverable(
                LocalDate.of(2021, 2, 10 + daysLate)
        );

        assertEquals(daysLate, deliverable.getDaysLate(LocalDate.of(2021, 2, 10)));
    }
}