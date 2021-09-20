package dev.andrylat.volha.bankingutilities.cardprocessing;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;

import java.util.stream.Stream;

public class CardValidator {
    public static final int CARD_NUMBER_LENGTH = 16;
    CardNumberException exceptionToThrow = new CardNumberException();

    public void validate(String number) throws CardNumberException {

        final String SPACE_REGEX = "\\s";
        if (number == null || number.isEmpty()) {
            exceptionToThrow.addMessage("Number cannot be undefined");
        } else {
            number = number.replaceAll(SPACE_REGEX, "");
            validateNumberForNumber(number);
            validateForCheckDigit(number);
        }
    }

    private void validateNumberForNumber(String input) throws CardNumberException {
        final String ONLY_DIGITS_REGEX = "[0-9]+";

        if (input.length() != CARD_NUMBER_LENGTH) {
            exceptionToThrow.addMessage("Card number must be " + CARD_NUMBER_LENGTH + " digits");
        }
        if (!input.matches(ONLY_DIGITS_REGEX)) {
            exceptionToThrow.addMessage("Card number must contain only numbers from 0 to 9.");
        }

        if (!exceptionToThrow.getMessages().isEmpty()) {
            throw exceptionToThrow;
        }
    }

    private void validateForCheckDigit(String input) throws CardNumberException {
        int checkSum = 0;
        int[] digits = Stream.of(input.split("")).
                mapToInt(Integer::parseInt).
                toArray();

        final int CHECK_DIGIT = digits[digits.length - 1];
        boolean evenPosition = true;

        for ( int i = digits.length - 2; i >= 0; i-- ) {
            if (evenPosition) {
                checkSum = checkSum + processEven(digits[i]);
            } else {
                checkSum = checkSum + digits[i];
            }
            evenPosition = !evenPosition;
        }

        if ((checkSum + CHECK_DIGIT) % 10 == 0) {
            return;
        } else {
            exceptionToThrow.addMessage("Сheck number is not valid");
        }
    }


    private int processEven(int digit) {
        if (digit * 2 < 10) {
            return digit * 2;
        } else {
            return digit * 2 / 10 + digit * 2 % 10;
        }
    }
}

