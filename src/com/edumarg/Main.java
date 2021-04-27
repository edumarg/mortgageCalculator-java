package com.edumarg;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Principal: ");
        int principal = scanner.nextInt();
        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = scanner.nextFloat();
        System.out.print("Period (years): ");
        byte periodYears = scanner.nextByte();
        // mortgage formula
        // M = mortgage
        // P = principal
        // r = monthly interest rate
        // n = period in months
        // M = P*[(r*(1+r)^n)/((1+r)^n-1)]
        final byte MONTHS_IN_A_YEAR = 12;
        int periodMonth = periodYears * MONTHS_IN_A_YEAR;
        float monthlyInterestRate = annualInterestRate / MONTHS_IN_A_YEAR;
        float ratePercentage = monthlyInterestRate / 100;
        double calcDivisor = Math.pow(1 + ratePercentage,periodMonth) * ratePercentage;
        double calcDividend = Math.pow(1 + ratePercentage,periodMonth) - 1;
        double mortgage = principal * calcDivisor / calcDividend;
        String result = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + result);

    }
}
