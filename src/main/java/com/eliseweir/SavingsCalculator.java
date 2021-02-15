package com.eliseweir;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator implements Calculator {
    private float[] credits, debits;
    private float netSavings = 0.0f;

    public SavingsCalculator(String[] args) {
        // get lists of credits and debits as strings
        String[] creditsAsString = args[0].split(",");
        String[] debitsAsString = args[1].split(",");

        // convert credits to numeric values
        int creditLength = creditsAsString.length;
        credits = new float[creditLength];
        for (int i = 0; i < creditLength; i++) {
            credits[i] = Utilities.getFloatValue(creditsAsString[i]);
        }

        // convert debits to numeric values
        int debitLength = debitsAsString.length;
        debits = new float[debitLength];
        for (int i = 0; i < debitLength; i++) {
            debits[i] = Utilities.getFloatValue(debitsAsString[i]);
        }
    }

    public void calculate() {
        netSavings = sumOfCredits() - sumOfDebits();
    }

    public String toString() {
        return "Net savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now());
    }

    private float sumOfCredits() {
        float sum = 0.0f;

        for (float debit : debits) sum += debit;

        return sum;
    }

    private float sumOfDebits() {
        float sum = 0.0f;

        for (float credit : credits) sum += credit;

        return sum;
    }

    /**
     * remainingDaysInMonth
     * @param date - current date
     * @return integer number of days remaining this month
     */
    private static int remainingDaysInMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        return totalDaysInMonth - date.getDayOfMonth();
    }
}
