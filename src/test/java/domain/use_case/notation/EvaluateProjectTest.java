package domain.use_case.notation;

import domain.model.notation.Deliverable;
import domain.model.notation.Mark;
import domain.model.notation.Project;
import domain.model.notation.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvaluateProjectTest {
    @InjectMocks
    EvaluateProject evaluateProject;

    @Mock
    ProjectRepository projects;

    @Mock
    StudentRepository students;

    @Test
    void shouldReturnZeroMarkWhenNoDeliverable() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            )
        );

        when(projects.findById("123")).thenReturn(project);
        doNothing().when(students).saveMany(project.getStudents());

        Mark actualMark = evaluateProject.evaluate("123", 10, "Good job");
        Mark expectedMark = new Mark(0, "No deliverable");

        assertEquals(expectedMark, actualMark);

        verify(projects).findById("123");
        verify(students).saveMany(project.getStudents());
        verifyNoMoreInteractions(projects, students);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, 19, 20})
    void shouldThrowExceptionWhenIrregularMarkHasNoComment(int markValue) {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(LocalDate.of(2021, 2, 15))
        );

        when(projects.findById("123")).thenReturn(project);

        assertThrows(MissingMarkCommentException.class, () -> {
            evaluateProject.evaluate("123", markValue, null);
        });

        verify(projects).findById("123");
        verifyNoInteractions(students);
        verifyNoMoreInteractions(projects);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, 19, 20})
    void shouldReturnMarkWhenIrregularMarkHasComment(int markValue) {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(LocalDate.of(2021, 2, 15))
        );

        when(projects.findById("123")).thenReturn(project);
        doNothing().when(students).saveMany(project.getStudents());

        Mark actualMark = evaluateProject.evaluate("123", markValue, "Good job");
        Mark expectedMark = new Mark(markValue, "Good job");

        assertEquals(expectedMark, actualMark);

        verify(projects).findById("123");
        verify(students).saveMany(project.getStudents());
        verifyNoMoreInteractions(projects, students);
    }

    @Test
    void shouldReturnZeroMarkWhenDeliverableIsTooLate() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(LocalDate.of(2021, 3, 20))
        );

        when(projects.findById("123")).thenReturn(project);
        doNothing().when(students).saveMany(project.getStudents());

        Mark actualMark = evaluateProject.evaluate("123", 10, null);
        Mark expectedMark = new Mark(0, "Too late");

        assertEquals(expectedMark, actualMark);

        verify(projects).findById("123");
        verify(students).saveMany(project.getStudents());
        verifyNoMoreInteractions(projects, students);
    }

    @Test
    void shouldReturnMarkWithLatePenaltyWhenDeliverableIsLate() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(LocalDate.of(2021, 2, 25))
        );

        when(projects.findById("123")).thenReturn(project);
        doNothing().when(students).saveMany(project.getStudents());

        Mark actualMark = evaluateProject.evaluate("123", 10, null);
        Mark expectedMark = new Mark(5, "Late by 5 days");

        assertEquals(expectedMark, actualMark);

        verify(projects).findById("123");
        verify(students).saveMany(project.getStudents());
        verifyNoMoreInteractions(projects, students);
    }

    @Test
    void shouldReturnMarkWhenDeliverableIsOnTime() {
        Project project = new Project(
            "123",
            LocalDate.of(2021, 2, 20),
            List.of(
                new Student("123"),
                new Student("456")
            ),
            new Deliverable(LocalDate.of(2021, 2, 20))
        );

        when(projects.findById("123")).thenReturn(project);
        doNothing().when(students).saveMany(project.getStudents());

        Mark actualMark = evaluateProject.evaluate("123", 10, null);
        Mark expectedMark = new Mark(10, null);

        assertEquals(expectedMark, actualMark);

        verify(projects).findById("123");
        verify(students).saveMany(project.getStudents());
        verifyNoMoreInteractions(projects, students);
    }

}