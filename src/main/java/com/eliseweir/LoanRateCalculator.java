package com.eliseweir;

import java.util.Map;
import java.util.Scanner;

public class LoanRateCalculator implements Calculator {
    public static final Map<Integer, Float> bestRates = Map.of(1, 5.50f, 2, 3.45f, 3, 2.67f);
    public float bestRate = 0.0f;
    public int loanTermInYears;
    private boolean validTerm = true;

    public LoanRateCalculator(String loanTermInYears) {
        try {
            this.loanTermInYears = Integer.parseInt(loanTermInYears);
        } catch (NumberFormatException e) {
            this.loanTermInYears = 0;
            validTerm = false;
        }

    }

    /**
     * calculates the best interest rates for a given loan term
     */
    public void calculate() {
        bestRate = bestRates.getOrDefault(loanTermInYears, 0.0f);
    }

    /**
     * toString produces data about loan rates for display.
     * @return string with rate information
     */
    public String toString() {
        if (!validTerm)
            return "Invalid loan term";
        else if (Float.compare(bestRate, 0.0f) == 0)
            return "No available rates for term: " + loanTermInYears + " years";
        else
            return "Best available rate: " + bestRate + "%";
    }
}
