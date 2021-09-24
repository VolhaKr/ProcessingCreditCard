package dev.andrylat.volha.bankingutilities.mortgagecalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MortgageCalculatorTest {
    private static MortgageCalculator mortgageCalculator;

    @BeforeAll
    public static void init() {
        mortgageCalculator = new MortgageCalculator();
    }

    @Test
    public void validateMortgageCalculationWhenSuccess() {
        double inputLoanAmount = 40000;
        int inputNumberOfYears = 10;
        double inputRate = 5;
        int paymentsPerYear = 12;
        BigDecimal expected = new BigDecimal(166.667);
        assertEquals(expected.subtract
                (mortgageCalculator.calculateMonthlyPaymentFixedRateMortgage(inputLoanAmount, inputNumberOfYears, inputRate, paymentsPerYear)).abs().
                compareTo(BigDecimal.valueOf(0.001)), -1);
    }
}
