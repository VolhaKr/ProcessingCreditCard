package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystem;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import dev.andrylat.volha.bankingutilities.mortgagecalculator.MortgageCalculator;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try (Scanner inScanner = new Scanner(System.in)) {
            OperationSelection operationSelection = new OperationSelection();
            operationSelection.run(inScanner);
        }
    }
}

