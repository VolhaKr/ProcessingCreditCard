package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.mortgagecalculator.MortgageCalculator;

import java.util.Scanner;

public class MortgageCalculation implements Dialog {
    @Override
    public void run(Scanner inScanner) {

        final int inputPaymentsPerYear = 12;
        double inputLoan = 0;
        double inputRate = 0;
        int inputNumberOfYears = 0;

        inputLoan = readDouble("Please, enter your loan", inScanner);
        inputRate = readDouble("Please, enter your rate", inScanner);

        while (!(inputNumberOfYears > 0)) {
            try {
                System.out.println("Please, enter number of years: ");
                inputNumberOfYears = Integer.parseInt(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                inputNumberOfYears = -1;
            }
        }

        MortgageCalculator mortgageCalculator = new MortgageCalculator();

        System.out.println("Your monthly paymet is ");
        System.out.println(mortgageCalculator.calculateMonthlyPaymentFixedRateMortgage(inputLoan, inputNumberOfYears, inputRate, inputPaymentsPerYear));
    }

    private static double readDouble(String message, Scanner inScanner) {
        double readDouble;
        do {
            try {
                System.out.println(message);
                readDouble = Double.parseDouble(inScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
                readDouble = -1;
            }
        }
        while (!(readDouble > 0));
        return readDouble;
    }
}


