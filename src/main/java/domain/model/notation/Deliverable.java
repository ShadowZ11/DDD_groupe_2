package domain.model.notation;

import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class Deliverable {
    private final LocalDate uploadDate;

    public Deliverable(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isLate(LocalDate deadline) {
        return uploadDate.isAfter(deadline);
    }

    public boolean isTooLate(LocalDate deadline) {
        return uploadDate.isAfter(deadline.plusDays(7));
    }

    public int getDaysLate(LocalDate deadline) {
        return (int) ChronoUnit.DAYS.between(deadline, uploadDate);
    }
}
