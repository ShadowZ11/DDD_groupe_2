package domain.model.notation;

import lombok.Getter;

@Getter
public class Teacher {
    private final String firstname;
    private final String lastname;

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
