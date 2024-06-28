package domain.model.notation;

import domain.use_case.notation.MissingMarkCommentException;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Mark {
    private final int value;
    private final String comment;

    public Mark(int value) {
        this.value = value;
        this.comment = null;
    }

    public Mark(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public boolean isIrregular() {
        return value < 6 || value > 18;
    }

    public boolean hasComment() {
        return comment != null;
    }

    public void checkCommentWhenIrregular() {
        if (isIrregular() && !hasComment()) {
            throw new MissingMarkCommentException("Justification required for irregular mark");
        }
    }

    public Mark getDeliverableMark(Deliverable deliverable, LocalDate deadline) {
        this.checkCommentWhenIrregular();

        if (deliverable.isLate(deadline)) {
            return this.getLateMark(deliverable, deadline);
        }

        return this;
    }

    public Mark getLateMark(Deliverable deliverable, LocalDate deadline) {
        if (deliverable.isTooLate(deadline)) {
            return AbnormalMark.TOO_LATE.getMark();
        } else {
            return this.getDaysLateMark(deliverable, deadline);
        }
    }

    public Mark getDaysLateMark(Deliverable deliverable, LocalDate deadline) {
        int daysLate = deliverable.getDaysLate(deadline);
        int newMarkValue = getValue() - daysLate;

        return new Mark(newMarkValue, "Late by " + daysLate + " days");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return value == mark.value && Objects.equals(comment, mark.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, comment);
    }
}
