package andrylat.dev.volha;

public class NoPayingSystemException extends Throwable {
    String message;

    public NoPayingSystemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
