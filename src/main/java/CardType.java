public enum CardType {
    // This will call enum constructor with one
    // String argument

    VISA(new String[]{"4"}),
    MASTERCARD(new String[]{"51", "52", "53", "54", "55"}),
    JCB(new String[]{"6011", "65"}),
    DINERS_CLUB(new String[]{"36", "38"}),
    DISCOVER(new String[]{"6011", "65"}),
    AMERICAN_EXPRESS(new String[]{"6011", "65"});

    String[] numberPrefixes;

    CardType(String[] numberPrefixes) {
        this.numberPrefixes = numberPrefixes;
    }

    String[] getNumberPrefixes() {
        return numberPrefixes;
    }

}
