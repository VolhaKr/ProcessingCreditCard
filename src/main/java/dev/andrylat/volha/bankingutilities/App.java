package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystem;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import dev.andrylat.volha.bankingutilities.mortgagecalculator.MortgageCalculator;

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
        StringBuilder output = new StringBuilder();
        try {
            cardValidator.validate(inputNumber);
            // } catch (CardNumberException | PaymentSystemException e) {
            //but then you cannot process this exceptions separately???
        } catch (CardNumberException e) {
            output.append("> Errors: " + System.lineSeparator());
            errorsAdded = true;
            for ( String message : e.getMessages() ) {
                output.append(">     " + message + System.lineSeparator());
            }
        }
        try {
            PaymentSystemResolver paymentSystemResolver = new PaymentSystemResolver();
            paymentSystem = paymentSystemResolver.resolve(inputNumber);
        } catch (PaymentSystemException e) {
            if (!errorsAdded) {
                output.append("> Errors: " + System.lineSeparator());
            }

            output.append(">     " + e.getMessage() + System.lineSeparator());
        }
        if (output.length() > 0) {
            System.out.println(output);
        } else {
            System.out.println("> Card is valid. Payment system is " + paymentSystem);
        }
    }

    private static void callMortageCalculation(Scanner inScanner) {
        final int inputPaymentsPerYear = 12;
        double inputLoan = 0;
        int inputNumberOfYears = 0;
        double inputRate = 0;

        inputLoan = readData("Please, enter your loan", inputLoan, inScanner);


        while (!(inputNumberOfYears > 0)) {
            try {
                System.out.println("Please, enter number of years: ");
                inputNumberOfYears = Integer.parseInt(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputNumberOfYears = -1;
            }
        }

        inputRate = readData("Please, enter your rate", inputRate, inScanner);

        MortgageCalculator mortgageCalculator = new MortgageCalculator();

        System.out.println("Your monthly paymet is ");
        System.out.println(mortgageCalculator.calculateMonthlyPaymentFixedRateMortgage(inputLoan, inputNumberOfYears, inputRate, inputPaymentsPerYear));
    }

    private static double readData(String message, double readData, Scanner inScanner) {
        while (!(readData > 0)) {
            try {
                System.out.println(message);
                readData = Double.parseDouble(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                readData = -1;
            }
        }
        return readData;
    }
}

