package domain.model.notation;

import lombok.Getter;

@Getter
public class Student {
    private final String firstname;
    private final String lastname;

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
