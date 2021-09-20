package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystem;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;
import dev.andrylat.volha.bankingutilities.mortagecalculator.MortageCalculator;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static String input = "";

    public static void main(String[] args) {
        System.out.println("Enter C to validate your card number and get it's payment system");
        System.out.println("Enter M to calculate your monthly payment to fixed-rate mortage");
        chooseOperation();
    }

    private static void chooseOperation() {
        Scanner inScanner = new Scanner(System.in);
        input = inScanner.nextLine();
        switch (input) {
            case "C": {
                callCardProcessing();
                break;
            }
            case "M": {
                callMortageCalculation();
                break;
            }
            default:
                System.out.println("There is no such operation - must be C or M.");
                break;
        }
    }

    private static void callCardProcessing() {
        System.out.println("Enter your card number: ");
        boolean errorsAdded = false;
        Scanner in = new Scanner(System.in);
        String inputNumber = in.nextLine();
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

    private static void callMortageCalculation() {
        final int inputPaymentsPerYear = 12;
        double inputLoan = 0;
        int inputNumberOfYears = 0;
        double inputRate = 0;
        while (!(inputLoan > 0)) {
            try (Scanner in = new Scanner(System.in)) {
                System.out.println("Please, enter your loan: ");
                inputLoan = Double.parseDouble(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputLoan = -1;
            }
        }

        System.out.println("Please, enter number of years: ");
        Scanner inS = new Scanner(System.in);
        inputNumberOfYears = Integer.parseInt(inS.nextLine());
        while (!(inputNumberOfYears > 0)) {
            System.out.println("Wrong number. Number of years must be >=0. ");
            inputNumberOfYears = Integer.parseInt(inS.nextLine());
        }
        System.out.println("Please, enter rate: ");
        inputRate = Double.parseDouble(inS.nextLine());
        while (!(inputRate > 0)) {
            System.out.println("Wrong rate. Rate must be >=0. ");
            inputRate = Double.parseDouble(inS.nextLine());
        }


        MortageCalculator mortageCalculator = new MortageCalculator();

        System.out.println("Your monthly paymet is ");
        System.out.println(mortageCalculator.calculateMonthlyPaymentFixedRateMortage(inputLoan, inputNumberOfYears, inputRate, inputPaymentsPerYear));
    }
}

