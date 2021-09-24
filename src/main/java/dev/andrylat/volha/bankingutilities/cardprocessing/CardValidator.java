package dev.andrylat.volha.bankingutilities.cardprocessing;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CardValidator {
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final String SPACE_REGEX = "\\s";
    private static final String ONLY_DIGITS_REGEX = "[0-9]+";
    List<String> errors = new ArrayList<>();

    public List<String> validate(String number) {


        if (number == null || number.isEmpty()) {
            errors.add("Number cannot be undefined");
        } else {
            number = number.replaceAll(SPACE_REGEX, "");
            validateIfNumber(number);
            if (errors.isEmpty()) {
                errors = validateCheckDigit(number);
            }
        }
        return errors;
    }

    private void validateIfNumber(String input) {
        if (input.length() != CARD_NUMBER_LENGTH) {
            errors.add("Card number must be " + CARD_NUMBER_LENGTH + " digits");
        }
        if (!input.matches(ONLY_DIGITS_REGEX)) {
            errors.add("Card number must contain only numbers from 0 to 9.");
        }
    }

    /**
     * The last digit of a card number is calculated by Luhn algorythm
     * https://datagenetics.com/blog/july42013/index.html
     */
    private List<String> validateCheckDigit(String input) {
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

        if (!((checkSum + CHECK_DIGIT) % 10 == 0)) {
            errors.add("Сheck number is not valid");
        }
        return errors;
    }

    private int processEven(int digit) {
        if (digit * 2 < 10) {
            return digit * 2;
        } else {
            return digit * 2 / 10 + digit * 2 % 10;
        }
    }
}

