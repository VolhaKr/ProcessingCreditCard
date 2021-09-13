public class App {
    public static void main(String[] args) {
        System.out.println("Please, enter you card number");
        String input = System.console().readLine();
        CreditCard creditCard= new CreditCard(input);
        //catch exception
    }
}
