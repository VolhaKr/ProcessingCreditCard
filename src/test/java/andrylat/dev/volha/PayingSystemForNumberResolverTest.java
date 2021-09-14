package andrylat.dev.volha;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//I think I used BeforeALl without this annotation earlier

class PayingSystemForNumberResolverTest {
    private PayingSystemForNumberResolver payingSystemForNumberResolver;

    @BeforeAll
    public void init() {
        payingSystemForNumberResolver = new PayingSystemForNumberResolver();
    }

    @Test
    public void validateResolveShouldThrowExceptionWhenWrongPrefix() {
        String input = "1111111111116176";
        assertThrows(NoPayingSystemException.class, () -> payingSystemForNumberResolver.resolve(input),
                "Please, check your input. There is no such paying system");
    }

    @Test
    public void validateResolveShouldReturnMastercard() throws NoPayingSystemException {
        String input = "5411111111116171";
        assertEquals(PayingSystem.MASTERCARD, payingSystemForNumberResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnVisa() throws NoPayingSystemException {
        String input = "4411111111116174";
        assertEquals(PayingSystem.VISA, payingSystemForNumberResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnDinersClub() throws NoPayingSystemException {
        String input = "3611111111116174";
        assertEquals(PayingSystem.DINERS_CLUB, payingSystemForNumberResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnDiscover() throws NoPayingSystemException {
        String input = "6011111111116173";
        assertEquals(PayingSystem.DISCOVER, payingSystemForNumberResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnJCB() throws NoPayingSystemException {
        String input = "3511111111116175";
        assertEquals(PayingSystem.JCB, payingSystemForNumberResolver.resolve(input));
    }

    @Test
    public void validateResolveShouldReturnAmericanExpress() throws NoPayingSystemException {
        String input = "3711111111116173";
        assertEquals(PayingSystem.AMERICAN_EXPRESS, payingSystemForNumberResolver.resolve(input));
    }

}