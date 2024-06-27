package domain.model.notation;

import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class Mark {
    private final int value;
    private String comment = null;

    public Mark(int value) {
        this.value = value;
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
}
