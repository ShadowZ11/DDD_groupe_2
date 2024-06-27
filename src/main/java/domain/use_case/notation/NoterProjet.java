package domain.use_case.notation;

import domain.model.notation.Deliverable;
import domain.model.notation.Mark;
import domain.model.notation.Project;
import domain.model.notation.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NoterProjet {
    private final ProjectRepository projects;
    private final StudentRepository students;

    public NoterProjet(ProjectRepository projects, StudentRepository students) {
        this.projects = projects;
        this.students = students;
    }

    public Mark noter(String projectId, int markValue, String markComment) {
        Project project = projects.findById(projectId);

        Mark mark = new Mark(markValue, markComment);

        if (!project.hasDeliverable()) {
            mark = new Mark(0, "No deliverable");
        } else {
            if (mark.isIrregular() && !mark.hasComment()) {
                throw new IllegalArgumentException("Justification required for irregular mark");
            }

            Deliverable deliverable = project.getDeliverable();
            LocalDate deadline = project.getDeadline();

            if (deliverable.isLate(deadline)) {
                if (deliverable.isTooLate(deadline)) {
                    mark = new Mark(0, "Too late");
                } else {
                    int daysLate = deliverable.getDaysLate(deadline);
                    int newMarkValue = mark.getValue() - daysLate;

                    mark = new Mark(newMarkValue, "Late by " + daysLate + " days");
                }
            }
        }

        for (Student student : project.getStudents()) {
            student.addMark(mark);
        }

        students.saveMany(project.getStudents());

        return mark;
    }
}
