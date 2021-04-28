package com.edumarg;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal = 0;
        float annualInterestRate = 0.0F;
        byte periodYears = 0;

        while (true) {
            System.out.print("Principal ($1K - $1M): ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
               break;
            }
            System.out.println("Enter a number between 1,000 and 1,000,000");
        }

        while (true) {
            System.out.print("Annual Interest Rate (0-30): ");
            annualInterestRate = scanner.nextFloat();
            if (annualInterestRate > 0 && annualInterestRate <= 30) {
               break;
            }
            System.out.println("Enter a value greater than 0 and less or equal than 30");
        }

        while (true) {
            System.out.print("Period (1-30 years): ");
            periodYears = scanner.nextByte();
            if (periodYears >= 1 && periodYears <=30) {
                break;
            }
            System.out.println("Enter a value between 1-30");
        }

        // mortgage formula
        // M = mortgage
        // P = principal
        // r = monthly interest rate
        // n = period in months
        // M = P*[(r*(1+r)^n)/((1+r)^n-1)]

        final byte MONTHS_IN_A_YEAR = 12;
        final byte PERCENTAGE = 100;
        int periodMonth = periodYears * MONTHS_IN_A_YEAR;
        float monthlyInterestRate = annualInterestRate / MONTHS_IN_A_YEAR;
        float ratePercentage = monthlyInterestRate / PERCENTAGE;
        double calcDivisor = Math.pow(1 + ratePercentage,periodMonth) * ratePercentage;
        double calcDividend = Math.pow(1 + ratePercentage,periodMonth) - 1;
        double mortgage = principal * calcDivisor / calcDividend;

        String result = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + result);

    }
}
