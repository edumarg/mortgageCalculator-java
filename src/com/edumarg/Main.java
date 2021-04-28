package com.edumarg;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal = setPrincipal(scanner);
        float annualInterestRate = setInterestRate(scanner);
        byte periodYears = setPeriod(scanner);

        double mortgage = calculateMortgage(principal, annualInterestRate, periodYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);

    }

    public static int setPrincipal(Scanner scanner){
        int principal = 0;
        while (true) {
            System.out.print("Principal ($1K - $1M): ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
                return principal;
            }
            System.out.println("Enter a number between 1,000 and 1,000,000");
        }

    }

    public static float setInterestRate( Scanner scanner){
        float annualInterestRate = 0.0F;
        while (true) {
            System.out.print("Annual Interest Rate (0-30): ");
            annualInterestRate = scanner.nextFloat();
            if (annualInterestRate > 0 && annualInterestRate <= 30) {
                return annualInterestRate;
            }
            System.out.println("Enter a value greater than 0 and less or equal than 30");
        }
    }

    public static byte setPeriod(Scanner scanner){
        byte periodYears = 0;
        while (true) {
            System.out.print("Period (1-30 years): ");
            periodYears = scanner.nextByte();
            if (periodYears >= 1 && periodYears <=30) {
                return periodYears;
            }
            System.out.println("Enter a value between 1-30");
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
