package domain.model.notation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    @Test
    void shouldHaveDeliverable() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(
                LocalDate.of(2021, 2, 20)
            )
        );

        assertTrue(project.hasDeliverable());
    }

    @Test
    void shouldNotHaveDeliverable() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            )
        );

        assertFalse(project.hasDeliverable());
    }
}