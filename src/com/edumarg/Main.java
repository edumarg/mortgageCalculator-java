package com.edumarg;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int principal = (int)readNumber("Principal ($1K - $1M): ",
                1000,
                1_000_000);
        float annualInterestRate = (float)readNumber("Annual Interest Rate (0.5-30): ",
                0.5,
                30);
        byte periodYears = (byte)readNumber("Period (1-30 years): ",
                1,
                30);

        double mortgage = calculateMortgage(principal, annualInterestRate, periodYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);

    }

    public static double readNumber(String inputMessage, double minValue , double maxValue){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(inputMessage);
            double input = scanner.nextDouble();
            if (input >= minValue && input <=maxValue) {
                return input;
            }
            System.out.println("Enter a number between " + minValue + " and " + maxValue);
        }
    }

    public static double calculateMortgage(int principal, float annualInterestRate,  int periodYears){

        // mortgage formula
        // M = mortgage
        // P = principal
        // r = monthly interest rate
        // n = period in months
        // M = P*[(r*(1+r)^n)/((1+r)^n-1)]

        final byte MONTHS_IN_A_YEAR = 12;
        final byte PERCENTAGE = 100;

        float monthlyInterestRate = annualInterestRate / MONTHS_IN_A_YEAR;
        float ratePercentage = monthlyInterestRate / PERCENTAGE;
        int periodMonth = periodYears * MONTHS_IN_A_YEAR;

        double calcDivisor = Math.pow(1 + ratePercentage,periodMonth) * ratePercentage;
        double calcDividend = Math.pow(1 + ratePercentage,periodMonth) - 1;
        return principal * calcDivisor / calcDividend;

    }
}
