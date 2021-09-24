package dev.andrylat.volha.bankingutilities.cardprocessing.exceptions;

import java.util.ArrayList;
import java.util.List;

public class CardNumberException extends Exception {
    private List<String> messages = new ArrayList<String>();

    public CardNumberException(List<String> errors) {
        this.messages = errors;
    }

    public List<String> getMessages() {
        return messages;
    }
}

