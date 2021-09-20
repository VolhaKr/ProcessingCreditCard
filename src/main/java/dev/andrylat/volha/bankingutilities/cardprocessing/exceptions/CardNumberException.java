package dev.andrylat.volha.bankingutilities.cardprocessing.exceptions;

import java.util.ArrayList;
import java.util.List;

public class CardNumberException extends Exception {
    private List<String> messages = new ArrayList<String>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}

