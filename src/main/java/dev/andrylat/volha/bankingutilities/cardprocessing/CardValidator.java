package dev.andrylat.volha.bankingutilities.cardprocessing;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;

import java.util.stream.Stream;

public class CardValidator {
    public static final int CARD_NUMBER_LENGTH = 16;

    public void validate(String number) throws CardNumberException {
        validateNumberForNumber(number);
        validateNumberForCheckDigit(number);
    }

    private void validateNumberForNumber(String input) throws CardNumberException {
        final String ONLY_DIGITS = "[0-9]+";
        if (input == null || input.isEmpty()) {
            throw new CardNumberException("Your input is wrong. Card number is not specified.");
        } else {
            input = input.replaceAll("\\s", "");
            if (input.length() != CARD_NUMBER_LENGTH) {
                throw new CardNumberException("Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
            } else {
                if (!input.matches(ONLY_DIGITS)) {
                    throw new CardNumberException("Your input is wrong. Card number must contain only numbers from 0 to 9.");
                }
            }
        }
    }

    private void validateNumberForCheckDigit(String input) throws CardNumberException {
        int checkSum = 0;

        int[] digits = Stream.of(input.split("")).
                mapToInt(Integer::parseInt).
                toArray();

        final int LENGTH_OF_DIGITS = digits.length;
        final int CHECK_DIGIT = digits[LENGTH_OF_DIGITS - 1];
        boolean evenPosition = true;

        for ( int i = LENGTH_OF_DIGITS - 2; i >= 0; i-- ) {
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
            throw new CardNumberException("Your input is wrong - check number is not valid");
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

