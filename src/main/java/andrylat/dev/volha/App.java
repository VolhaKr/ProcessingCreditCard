package andrylat.dev.volha;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Please, enter you card number: ");
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        //doesn't work -  String input = System.console().readLine();
        CardNumberValidator cardNumberValidator = new CardNumberValidator(input);

        try {
            cardNumberValidator.validate();
            PaymentSystemResolver paymentSystemResolver = new PaymentSystemResolver();
            System.out.println("Card payment system is "+ paymentSystemResolver.resolve(input));
        } catch (CardNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PaymentSystemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
