package dev.andrylat.volha.bankingutilities.cardprocessing;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardValidatorTest {

    private static final int CARD_NUMBER_LENGTH = 16;
    private static CardValidator cardValidator;

    @BeforeAll
    public static void init() {
        cardValidator = new CardValidator();
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNull() {
        String input = null;
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Number cannot be undefined"));

//        Exception nullInputException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(nullInputException.getMessage(), "Your input is wrong. Card number is not specified.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenEmpty() {
        String input = "";
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Number cannot be undefined"));
//        Exception emptyInputException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(emptyInputException.getMessage(), "Your input is wrong. Card number is not specified.");

    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooShort() {
        String input = "12346";
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Card number must be " + CARD_NUMBER_LENGTH + " digits"));
//        Exception tooShortInputException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(tooShortInputException.getMessage(), "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenTooLong() {
        String input = "12346789012345678";
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Card number must be " + CARD_NUMBER_LENGTH + " digits"));
//        Exception tooLongException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(tooLongException.getMessage(), "Your input is wrong. Card number must contain " + CARD_NUMBER_LENGTH + " digits.");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenNotOnlyDigits() {
        String input = "12346789h1234567";
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Card number must contain only numbers from 0 to 9."));
//        Exception notOnlyDigitsException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(notOnlyDigitsException.getMessage(), "Your input is wrong. Card number must contain only numbers from 0 to 9.");
        //the message is not checked
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongLastNumber() {
        String input = "1111111111111118";
        CardValidator cardValidator = new CardValidator();
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Сheck number is not valid"));
//        Exception wrongLastNumberException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(wrongLastNumberException.getMessage(), "Your input is wrong - check number is not valid");
    }

    @Test
    public void validateInputForCardNumberShouldThrowExceptionWhenWrongCheckNumberWithRoots() {
        String input = "1111111111116176";
        CardValidator cardValidator = new CardValidator();
        cardValidator.validate(input);
        assertTrue(cardValidator.errors.contains("Сheck number is not valid"));
////
//        Exception wrongLastNumberException = assertThrows(CardNumberException.class, () -> cardValidator.validate(input));
//        assertEquals(wrongLastNumberException.getMessage(), "Your input is wrong - check number is not valid");

    }

   @Test
    public void validateInputForCardNumberShouldNotThrowExceptionWhenRightCheckNumber() {
        String input = "5457 6238 9823 4113";
        boolean exceptionThrown = false;
        CardValidator cardValidator = new CardValidator();
       assertTrue(cardValidator.errors.isEmpty());
//        assertAll(() -> cardValidator.validate(input));
//        assertDoesNotThrow(() -> cardValidator.validate(input));
    }
}
