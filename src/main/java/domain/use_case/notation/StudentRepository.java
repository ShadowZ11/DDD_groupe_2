package domain.use_case.notation;

import domain.model.notation.Student;

public interface StudentRepository {
    void save(Student student);
}
