package domain.model.notation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public record Deliverable(LocalDate uploadDate) {

    public boolean isLate(LocalDate deadline) {
        return uploadDate.isAfter(deadline);
    }

    public boolean isTooLate(LocalDate deadline) {
        return uploadDate.isAfter(deadline.plusDays(7));
    }

    public int getDaysLate(LocalDate deadline) {
        return (int) ChronoUnit.DAYS.between(deadline, uploadDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverable that = (Deliverable) o;
        return Objects.equals(uploadDate, that.uploadDate);
    }

}
