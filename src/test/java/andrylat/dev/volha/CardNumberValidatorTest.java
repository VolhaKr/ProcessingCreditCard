package andrylat.dev.volha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardNumberValidatorTest {

    private static final int CARD_NUMBER_LENGTH = 16;
    //how to use the variable from andrylat.dev.volha.CardNumberValidator.*?

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNull() {
        String input = null;
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong. Card number is not specified.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenEmpty() {
        String input = "";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong. Card number is not specified.");

    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooShort() {
        String input = "12346";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooLong() {
        String input = "1234678901234567";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNotOnlyDigits() {
        String input = "12346789h1234567";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong. Card number must contain only numbers from 0 to 9.");
        //the message is not checked
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumber() {
        String input = "1111111111111118";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumberWithRoots() {
       String input = "1111111111116176";
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        assertThrows(CardNumberException.class, () -> cardNumberValidator.validate(),
                "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldNotThrowExceptionWhenRightCheckNumberWithRoots() {
        String input = "1111111111116173";
        boolean exceptionThrown = false;
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);
        try {
            cardNumberValidator.validate();
        } catch (CardNumberException e) {
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }
}