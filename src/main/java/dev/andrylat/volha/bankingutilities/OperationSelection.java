package dev.andrylat.volha.bankingutilities;

import java.util.Scanner;

public class OperationSelection implements Dialog {
    final String CARD_VALIDATION_OPERTAION = "C";
    final String MORTGAGE_CALCULATION_OPERATION = "M";

    @Override
    public void run(Scanner inScanner) {
        System.out.println("Enter C to validate your card number and get it's payment system");
        System.out.println("Enter M to calculate your monthly payment to fixed-rate mortage");
        chooseOperation(inScanner);
    }

    private void chooseOperation(Scanner inScanner) {

        String input = inScanner.nextLine();
        switch (input) {
            case CARD_VALIDATION_OPERTAION: {
                CardValidation cardValidation = new CardValidation();
                cardValidation.run(inScanner);
                break;
            }
            case MORTGAGE_CALCULATION_OPERATION: {
                MortgageCalculation mortgageCalculation = new MortgageCalculation();
                mortgageCalculation.run(inScanner);
                break;
            }
            default:
                System.out.println("There is no such operation - must be C or M.");
                break;
        }
    }
}

