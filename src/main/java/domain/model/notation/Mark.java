package domain.model.notation;

import lombok.Getter;

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
