package domain.use_case.notation;

import domain.model.notation.Project;

public interface ProjectRepository {
    Project findById(String projetId);

    void save(Project project);
}
