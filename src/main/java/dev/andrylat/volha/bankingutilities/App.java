package dev.andrylat.volha.bankingutilities;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem.PaymentSystemResolver;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.PaymentSystemException;

import java.util.Scanner;

public class App {
    private static String input = "";

    public static void main(String[] args) {
        //  while (input!="0") {
        System.out.println("What do you want to do?");
        System.out.println("Enter C if you want to validate your card number and get it's payment system");
        System.out.println("Enter M if you want to calculate your monthly payment to fixed-rate mortage");
        System.out.println("Enter E if you want to validate your card number and get it's payment system");
        chooseOperation();
    }
    //  }

    private static String chooseOperation() {
        Scanner inScanner = new Scanner(System.in);
        //while (input != "0") {
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
                case "E": {
                    inScanner.close();
                    break;
                }
                default:
                    System.out.println("Please, enter 1 or 2 or 0");
                    break;
            }
      //  }
        return input;
    }

    private static void callCardProcessing() {
        System.out.println("Please, enter you card number: ");
        Scanner in = new Scanner(System.in);
        String inputNumber = in.nextLine();

        CardValidator cardValidator = new CardValidator();

        try {
            cardValidator.validate(inputNumber);
            PaymentSystemResolver paymentSystemResolver = new PaymentSystemResolver();
            System.out.println("Card payment system is " + paymentSystemResolver.resolve(inputNumber));
        } catch (CardNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PaymentSystemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void callMortageCalculation() {

    }
}
