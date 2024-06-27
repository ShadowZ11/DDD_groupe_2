package domain.model.notation;

import lombok.Getter;

@Getter
public class Mark {
    private final int value;
    private final int max;
    private String comment = null;

    public Mark(int value, int max) {
        this.value = value;
        this.max = max;
    }
}
