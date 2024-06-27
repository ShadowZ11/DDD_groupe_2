package domain.model.notation;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Project {
    private String id;
    private String name;
    private LocalDate deadline;
    private List<Student> students;
    private Teacher teacher;
    private Deliverable deliverable = null;
    private Mark mark;

    public Project(String id, String name, LocalDate deadline, List<Student> students, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.students = students;
        this.teacher = teacher;
    }
}
