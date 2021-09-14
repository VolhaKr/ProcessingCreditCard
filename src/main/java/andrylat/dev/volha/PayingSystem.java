package andrylat.dev.volha;

public enum PayingSystem {
    VISA("4"),
    MASTERCARD("51", "52", "53", "54", "55"),
    DINERS_CLUB("36", "38"),
    DISCOVER("6011", "65"),
    JCB("35"),
    AMERICAN_EXPRESS("34", "37");

    private String[] numberPrefixes;

    PayingSystem(String... numberPrefixes) {
        this.numberPrefixes = numberPrefixes;
    }

    String[] getNumberPrefixes() {
        return numberPrefixes;
    }

}

