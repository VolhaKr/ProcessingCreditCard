package dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem;

import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;

public class PaymentSystemResolver {

    public PaymentSystem resolve(String input) throws PaymentSystemException {
        for ( PaymentSystem paymentSystem : PaymentSystem.values() ) {
            for ( String numberPrefix : paymentSystem.getNumberPrefixes() ) {
                if (input.startsWith(numberPrefix)) {
                    return paymentSystem;
                }
            }
        }
        throw new PaymentSystemException("There is no such payment system");
    }
}

