package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystem;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import dev.andrylat.volha.bankingutilities.mortagecalculator.MortageCalculator;

import java.util.Scanner;

public class App {
    private static String input = "";

    public static void main(String[] args) {

        System.out.println("Enter C to validate your card number and get it's payment system");
        System.out.println("Enter M to calculate your monthly payment to fixed-rate mortage");
        try (Scanner inScanner = new Scanner(System.in)) {
            chooseOperation(inScanner);
        }
    }

    private static void chooseOperation(Scanner inScanner) {
        input = inScanner.nextLine();
        switch (input) {
            case "C": {
                callCardProcessing(inScanner);
                break;
            }
            case "M": {
                callMortageCalculation(inScanner);
                break;
            }
            default:
                System.out.println("There is no such operation - must be C or M.");
                break;
        }
    }

    private static void callCardProcessing(Scanner inScanner) {
        System.out.println("Enter your card number: ");
        boolean errorsAdded = false;
        String inputNumber = inScanner.nextLine();
        PaymentSystem paymentSystem = null;
        CardValidator cardValidator = new CardValidator();
        StringBuilder outputString = new StringBuilder();
        try {
            cardValidator.validate(inputNumber);
            // } catch (CardNumberException | PaymentSystemException e) {
            //but then you cannot process this exceptions separately???
        } catch (CardNumberException e) {
            outputString.append("> Errors: " + System.lineSeparator());
            errorsAdded = true;
            for ( String message : e.getMessages() ) {
                outputString.append(">     " + message + System.lineSeparator());
            }
        }
        try {
            PaymentSystemResolver paymentSystemResolver = new PaymentSystemResolver();
            paymentSystem = paymentSystemResolver.resolve(inputNumber);
        } catch (PaymentSystemException e) {
            if (!errorsAdded) {
                outputString.append("> Errors: " + System.lineSeparator());
            }

            outputString.append(">     " + e.getMessage() + System.lineSeparator());
        }
        if (outputString.length() > 0) {
            System.out.println(outputString);
        } else {
            System.out.println("> Card is valid. Payment system is " + paymentSystem);
        }
    }

    private static void callMortageCalculation(Scanner inScanner) {
        final int inputPaymentsPerYear = 12;
        double inputLoan = 0;
        int inputNumberOfYears = 0;
        double inputRate = 0;
        while (!(inputLoan > 0)) {
            try {
                System.out.println("Please, enter your loan: ");
                inputLoan = Double.parseDouble(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputLoan = -1;
            }
        }

        while (!(inputNumberOfYears > 0)) {
            try {
                System.out.println("Please, enter number of years: ");
                inputNumberOfYears = Integer.parseInt(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputNumberOfYears = -1;
            }
        }

        while (!(inputRate > 0)) {
            try {
                System.out.println("Please, enter rate: ");
                inputRate = Double.parseDouble(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputRate = -1;
            }
        }

        MortageCalculator mortageCalculator = new MortageCalculator();
        System.out.println("Your monthly paymet is ");
        System.out.println(mortageCalculator.calculateMonthlyPaymentFixedRateMortage(inputLoan, inputNumberOfYears, inputRate, inputPaymentsPerYear));
    }

}

