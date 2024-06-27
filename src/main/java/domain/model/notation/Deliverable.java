package domain.model.notation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Deliverable {
    private final LocalDate uploadDate;

    public Deliverable(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
