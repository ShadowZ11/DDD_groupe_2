package domain.use_case.notation;

import domain.model.notation.Mark;
import domain.model.notation.Project;

public class EvaluateProject {
    private final ProjectRepository projects;
    private final StudentRepository students;

    public EvaluateProject(ProjectRepository projects, StudentRepository students) {
        this.projects = projects;
        this.students = students;
    }

    public Mark evaluate(String projectId, int markValue, String markComment) {
        Project project = projects.findById(projectId);

        Mark mark = project.getMark(markValue, markComment);

        students.saveMany(project.getStudents());

        return mark;
    }
}
