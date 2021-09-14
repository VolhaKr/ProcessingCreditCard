package andrylat.dev.volha;

public class PaymentSystemResolver {

    public PaymentSystem resolve(String input) throws PaymentSystemException {
        for ( PaymentSystem paymentSystem : PaymentSystem.values() ) {
            for ( String numberPrefix : paymentSystem.getNumberPrefixes() ) {
                if (input.startsWith(numberPrefix)) {
                    return paymentSystem;
                }
            }
        }
        throw new PaymentSystemException("Please, check your input. There is no such payment system");
    }
}

