package dev.andrylat.volha.bankingutilities.mortagecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortageCalculator {
    public BigDecimal calculateMonthlyPaymentFixedRateMortage(double inputLoanAmount, int numberOfYears, double inpuRate, int paymentsPerYear) {
        System.out.println();
        BigDecimal loanAmount = new BigDecimal(inputLoanAmount);
        BigDecimal rate = new BigDecimal(inpuRate);
        int overallNumberOfPayments = paymentsPerYear * numberOfYears;
        BigDecimal ratePerPeriod = rate.divide(new BigDecimal(paymentsPerYear), 6, RoundingMode.HALF_DOWN);
        BigDecimal onePlusRateToNPow = ratePerPeriod.add(new BigDecimal(1)).pow(overallNumberOfPayments);
        BigDecimal divident = onePlusRateToNPow.multiply(ratePerPeriod).multiply(loanAmount).divide(new BigDecimal(100));
        BigDecimal divisor = onePlusRateToNPow.subtract(new BigDecimal(1));
        BigDecimal paymentPerMonth = divident.divide(divisor, 6, RoundingMode.HALF_DOWN);
        return paymentPerMonth;
    }
}
