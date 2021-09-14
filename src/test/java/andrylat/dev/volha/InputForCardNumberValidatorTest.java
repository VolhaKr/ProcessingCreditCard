package andrylat.dev.volha;

import org.junit.jupiter.api.Test;
import andrylat.dev.volha.InputForCardNumberValidator.*;

import static org.junit.jupiter.api.Assertions.*;

class InputForCardNumberValidatorTest {

    private static final int CARD_NUMBER_LENGTH = 16;
    //how to use the variable from andrylat.dev.volha.InputForCardNumberValidator.*?

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNull() {
        String input = null;
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong. Card number is not specified.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenEmpty() {
        String input = "";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong. Card number is not specified.");

    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooShort() {
        String input = "12346";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooLong() {
        String input = "1234678901234567";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNotOnlyDigits() {
        String input = "12346789h1234567";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong. Card number must contain only numbers from 0 to 9.");
        //the message is not checked
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumber() {
        String input = "1111111111111118";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumberWithRoots() {
       String input = "1111111111116176";
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> inputForCardNumberValidator.validateInputForCardNumber(),
                "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenRightCheckNumberWithRoots() {
        String input = "1111111111116173";
        boolean exceptionThrown = false;
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);
        try {
            inputForCardNumberValidator.validateInputForCardNumber();
        } catch (CardNumberException e) {
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }
}