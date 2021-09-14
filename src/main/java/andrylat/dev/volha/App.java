package andrylat.dev.volha;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Please, enter you card number: ");
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        //doesn't work -  String input = System.console().readLine();
        InputForCardNumberValidator inputForCardNumberValidator = new InputForCardNumberValidator(input);

        try {
            inputForCardNumberValidator.validateInputForCardNumber();
            PayingSystemForNumberResolver payingSystemForNumberResolver = new PayingSystemForNumberResolver();
            System.out.println(payingSystemForNumberResolver.resolve(input));
        } catch (CardNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoPayingSystemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
