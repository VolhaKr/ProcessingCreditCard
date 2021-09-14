package andrylat.dev.volha;

public class PayingSystemForNumberResolver {

    public PayingSystem resolve(String input) throws NoPayingSystemException {
        for ( PayingSystem payingSystem : PayingSystem.values() ) {
            for ( String numberPrefix : payingSystem.getNumberPrefixes() ) {
                if (input.startsWith(numberPrefix)) {
                    return payingSystem;
                }
            }
        }
        throw new NoPayingSystemException("Please, check your input. There is no such paying system");
    }
}

