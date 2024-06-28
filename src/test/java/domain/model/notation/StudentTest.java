package domain.model.notation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void shouldAddMark() {
        Student student = new Student("123");

        Mark mark = new Mark(10, "Good job");

        student.addMark(mark);

        assertEquals(1, student.getMarks().size());
        assertEquals(mark, student.getMarks().getFirst());
    }
}