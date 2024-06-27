package domain.use_case.notation;

public class MissingMarkCommentException extends RuntimeException{
    public MissingMarkCommentException(String message) {
        super(message);
    }
}
