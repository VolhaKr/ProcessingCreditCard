package dev.andrylat.volha.bankingutilities.cardprocessing.exceptions;

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
