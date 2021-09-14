package andrylat.dev.volha;

import java.util.stream.Stream;

public class InputForCardNumberValidator {
    public final int CARD_NUMBER_LENGTH = 16;

    private String input;

    public InputForCardNumberValidator(String input) {
        this.input = input;
    }

    public void validateInputForCardNumber() throws CardNumberException {
        validateNumberForNumber();
        validateNumberForCheckNumber();
    }

    private void validateNumberForNumber() throws CardNumberException {

        if (input == null || input.isEmpty()) {
            throw new CardNumberException("Your input is wrong. Card number is not specified.");
        } else {
            if (input.length() != CARD_NUMBER_LENGTH) {
                throw new CardNumberException("Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
            } else {
                if (!input.matches("[0-9]+")) {
                    throw new CardNumberException("Your input is wrong. Card number must contain only numbers from 0 to 9.");
                }
            }
        }
    }

    private void validateNumberForCheckNumber() throws CardNumberException {
        int checkSum = 0;

        int[] digits = Stream.of(input.split("")).mapToInt(Integer::parseInt).toArray();

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

