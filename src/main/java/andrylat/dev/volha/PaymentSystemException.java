package andrylat.dev.volha;

public class PaymentSystemException extends Throwable {
    private String message;

    public PaymentSystemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
