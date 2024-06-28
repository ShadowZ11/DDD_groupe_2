package domain.model.notation;

import domain.use_case.notation.MissingMarkCommentException;

import java.time.LocalDate;

public record Mark(int value, String comment) {

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
        int newMarkValue = value() - daysLate;

        return new Mark(newMarkValue, "Late by " + daysLate + " days");
    }

}
