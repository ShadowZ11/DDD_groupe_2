package domain.use_case.notation;

import domain.model.notation.Student;

import java.util.List;

public interface StudentRepository {
    void saveMany(List<Student> students);
}
