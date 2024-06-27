package domain.use_case.notation;

import domain.model.notation.Mark;
import domain.model.notation.Project;

public class NoterProjet {
    private final ProjectRepository projects;

    public NoterProjet(ProjectRepository projectRepository) {
        this.projects = projectRepository;
    }

    /*Règles notation

    si note < 6 ou note > 18 : justification demandée

    si rendu en retard:

            1 point de malus par jour de retard
    si plus de 7 jours de retard: 0

    si pas de rendu: 0*/
    public Mark noter(String projetId) {
        Project project = projects.findById(projetId);

        Mark mark = project.getMark();

        if (project.getDeliverable() == null) {
            // set to 0
        }

        if ((mark.getValue() < 6 || mark.getValue() > 18) && mark.getComment() == null) {
            //throw exception
        }
        
        return mark;
    }
}
