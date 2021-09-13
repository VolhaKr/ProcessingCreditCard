public class CreditCard {
    CardType payingSystem;
    String number;

    public CreditCard(String number) {
        if (validateNumberForNumber(number)) {
            payingSystem = validateNumberForPayingSystem(number);
            this.number = number;
        }

    }

    private boolean validateNumberForNumber(String number) {
        if ((number.length() == 16) && (number.matches("[0-9]+"))) {
            return true;
        } else {
            return false;
        }
    }

    private CardType validateNumberForPayingSystem(String number) {
        for ( CardType cardType : CardType.values() ) {
            for ( String numberPrefix : cardType.numberPrefixes ) {
                if (number.startsWith(numberPrefix)) {
                    return cardType;
                }
            }
        }
        return null;
    }
}
