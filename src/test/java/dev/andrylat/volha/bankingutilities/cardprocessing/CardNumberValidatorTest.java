package dev.andrylat.volha.bankingutilities.cardprocessing;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardNumberValidatorTest {

    private static final int CARD_NUMBER_LENGTH = 16;
    private CardValidator cardValidator;
    //how to use the variable from dev.andrylat.volha.bankingutils.cardprocessing.CardValidator.*?

    @BeforeAll
    public void init() {
        cardValidator = new CardValidator();
    }

        @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNull() {
        String input = null;

        assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong. Card number is not specified.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenEmpty() {
        String input = "";
        assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong. Card number is not specified.");

    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooShort() {
        String input = "12346";
        assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooLong() {
        String input = "1234678901234567";
        assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNotOnlyDigits() {
        String input = "12346789h1234567";
       assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong. Card number must contain only numbers from 0 to 9.");
        //the message is not checked
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumber() {
        String input = "1111111111111118";
        CardValidator cardValidator = new CardValidator();
        assertThrows(CardNumberException.class, () -> cardValidator.validate(input),
                "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumberWithRoots() {
       String input = "1111111111116176";
        CardValidator cardValidator = new CardValidator();
        Exception e = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
        assertEquals(e.getMessage(),"Your input is wrong - check number is not valid");

    }

    @Test
    public void validateInputForCardNumberShouldNotThrowExceptionWhenRightCheckNumberWithRoots() {
        String input = "1111111111116173";
        boolean exceptionThrown = false;
        CardValidator cardValidator = new CardValidator();
        try {
            cardValidator.validate(input);
        } catch (CardNumberException e) {
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }
}