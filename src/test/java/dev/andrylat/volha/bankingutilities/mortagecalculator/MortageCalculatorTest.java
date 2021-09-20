package dev.andrylat.volha.bankingutilities.mortagecalculator;

import dev.andrylat.volha.bankingutilities.cardprocessing.CardValidator;
import dev.andrylat.volha.bankingutilities.cardprocessing.exceptions.CardNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MortageCalculatorTest {
    private static MortageCalculator mortageCalculator;

    @BeforeAll
    public static void init() {
        mortageCalculator = new MortageCalculator();
    }

    @Test
    public void validateMortageCalculationWhenSuccess() {
        double inputLoanAmount = 40000;
        int inputNumberOfYears = 10;
        double inputRate = 5;
        int paymentsPerYear = 12;
        BigDecimal expected = new BigDecimal(166.667);
        assertEquals(expected.subtract
                (mortageCalculator.calculateMonthlyPaymentFixedRateMortage(inputLoanAmount, inputNumberOfYears, inputRate, paymentsPerYear)).abs().
                compareTo(BigDecimal.valueOf(0.001)), -1);
    }
}