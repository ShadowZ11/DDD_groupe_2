package domain.model.notation;

import lombok.Getter;

@Getter
public enum AbnormalMark {
    TOO_LATE(new Mark(0, "Too late")),
    NO_DELIVERABLE(new Mark(0, "No deliverable"));

    private final Mark mark;

    AbnormalMark(Mark mark) {
        this.mark = mark;
    }
}