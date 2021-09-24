package dev.andrylat.volha.bankingutilities.cardprocessing.paymentsystem;

public enum PaymentSystem {
    VISA("4"),
    MASTERCARD("51", "52", "53", "54", "55"),
    DINERS_CLUB("36", "38"),
    DISCOVER("6011", "65"),
    JCB("35"),
    AMERICAN_EXPRESS("34", "37");

    private String[] prefixes;

    PaymentSystem(String... numberPrefixes) {
        this.prefixes = numberPrefixes;
    }

    String[] getNumberPrefixes() {
        return prefixes;
    }
}


