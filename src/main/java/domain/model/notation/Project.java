package domain.model.notation;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Project {
    private final String id;
    private final LocalDate deadline;
    private final List<Student> students;
    private Deliverable deliverable = null;

    public Project(String id, LocalDate deadline, List<Student> students) {
        this.id = id;
        this.deadline = deadline;
        this.students = students;
    }

    public Project(String id, LocalDate deadline, List<Student> students, Deliverable deliverable) {
        this.id = id;
        this.deadline = deadline;
        this.students = students;
        this.deliverable = deliverable;
    }

    public boolean hasDeliverable() {
        return deliverable != null;
    }
}
