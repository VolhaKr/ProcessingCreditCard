package dev.andrylat.volha.bankingutilities.mortgagecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageCalculator {
    public BigDecimal calculateMonthlyPaymentFixedRateMortgage(double inputLoanAmount, int numberOfYears, double inputRate, int paymentsPerYear) {
        final BigDecimal HUNDRED_PERCENT = new BigDecimal(100);
        BigDecimal loanAmount = new BigDecimal(inputLoanAmount);
        BigDecimal rate = new BigDecimal(inputRate);

        int overallNumberOfPayments = paymentsPerYear * numberOfYears;
        BigDecimal ratePerPeriod = rate.divide(new BigDecimal(paymentsPerYear), 6, RoundingMode.HALF_DOWN);

        BigDecimal onePlusRateToNPow = ratePerPeriod.add(BigDecimal.ONE).pow(overallNumberOfPayments);
        BigDecimal divident = onePlusRateToNPow.multiply(ratePerPeriod).multiply(loanAmount).divide(HUNDRED_PERCENT);
        BigDecimal divisor = onePlusRateToNPow.subtract(BigDecimal.ONE);
        BigDecimal paymentPerMonth = divident.divide(divisor, 6, RoundingMode.HALF_DOWN);

        return paymentPerMonth;
    }
}
