package dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//I think I used BeforeALl without this annotation earlier

class PaymentSystemResolverTest {
    private PaymentSystemResolver paymentSystemResolver;

    @BeforeAll
    public void init() {
        paymentSystemResolver = new PaymentSystemResolver();
    }

    @Test
    public void validateResolveShouldThrowExceptionWhenWrongPrefix() {
        String input = "1111111111116176";
        assertThrows(PaymentSystemException.class, () -> paymentSystemResolver.resolve(input),
                "Please, check your input. There is no such paying system");
    }

    @Test
    public void validateResolveShouldReturnMastercard() throws PaymentSystemException {
        String input = "5411111111116171";
        assertEquals(PaymentSystem.MASTERCARD, paymentSystemResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnVisa() throws PaymentSystemException {
        String input = "4411111111116174";
        assertEquals(PaymentSystem.VISA, paymentSystemResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnDinersClub() throws PaymentSystemException {
        String input = "3611111111116174";
        assertEquals(PaymentSystem.DINERS_CLUB, paymentSystemResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnDiscover() throws PaymentSystemException {
        String input = "6011111111116173";
        assertEquals(PaymentSystem.DISCOVER, paymentSystemResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnJCB() throws PaymentSystemException {
        String input = "3511111111116175";
        assertEquals(PaymentSystem.JCB, paymentSystemResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnAmericanExpress() throws PaymentSystemException {
        String input = "3711111111116173";
        assertEquals(PaymentSystem.AMERICAN_EXPRESS, paymentSystemResolver.resolve(input));
    }

}