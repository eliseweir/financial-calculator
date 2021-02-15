package com.eliseweir;

import java.text.DecimalFormat;

public class MortgageCalculator implements Calculator {
    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment = 0.0;

    public MortgageCalculator(String[] args) {
        loanAmount = Utilities.getLongValue(args[0]);
        termInYears = Utilities.getIntValue(args[1]);
        annualRate = Utilities.getFloatValue(args[2]);
    }

    public void calculate() {
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();

        // calculate monthly payment using M = P(r(1+r)^n/(((1+r)^n)-1)
        double M = P * (r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1));
        monthlyPayment = M;
    }

    private float getMonthlyInterestRate() {
        float interestRate = annualRate / 100;
        float monthlyRate = interestRate / 12;

        return monthlyRate;
    }

    private int getNumberOfPayments() { return 12 * termInYears; }

    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return "Monthly payment: $" + df.format(monthlyPayment);
    }
}
