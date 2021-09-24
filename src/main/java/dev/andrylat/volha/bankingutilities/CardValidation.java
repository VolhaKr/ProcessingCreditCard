package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystem;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardValidation implements Dialog {

    @Override
    public void run(Scanner inScanner) {

        System.out.println("Enter your card number: ");

        List<String> errors;
        String inputNumber = inScanner.nextLine();

        CardValidator cardValidator = new CardValidator();
        errors = cardValidator.validate(inputNumber);

/**
 *nothing is done,
 *  errors are processed in the next catch
 **/

        PaymentSystem paymentSystem;
        try {
            PaymentSystemResolver paymentSystemResolver = new PaymentSystemResolver();
            paymentSystem = paymentSystemResolver.resolve(inputNumber);
            System.out.println("> Card is valid. Payment system is " + paymentSystem);
        } catch (PaymentSystemException e) {
            errors.add(e.getMessage());
        }
        printErrors(errors);
    }

    private void printErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            StringBuilder output = new StringBuilder();
            output.append("> Errors: " + System.lineSeparator());
            for ( String e : errors ) {
                output.append(">     " + e + System.lineSeparator());
            }
            System.out.println(output);
        }
    }
}

