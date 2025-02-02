package domain.model.notation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Student {
    private final String id;
    private final List<Mark> marks = new ArrayList<>();

    public Student(String id) {
        this.id = id;
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }
}
