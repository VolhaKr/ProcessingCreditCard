package andrylat.dev.volha;

public class CardNumberException extends Exception {
    private String message;

    public CardNumberException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
