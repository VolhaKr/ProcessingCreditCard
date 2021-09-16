package dev.andrylat.volha.bankingutilities.cardprocessing.exceptions;

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
