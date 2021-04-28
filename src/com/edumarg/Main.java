package com.edumarg;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_A_YEAR = 12;
    final static byte PERCENTAGE = 100;

    public static void main(String[] args) {

        int principal = (int)readNumber(
                "Principal ($1K - $1M): ",
                1000,
                1_000_000);
        float annualInterestRate = (float)readNumber(
                "Annual Interest Rate (0.5-30): ",
                0.5,
                30);
        byte periodYears = (byte)readNumber(
                "Period (1-30 years): ",
                1,
                30);

        int periodMonths = calculatePeriodMonths(periodYears);
        float monthlyRate = calculateMonthlyRate(annualInterestRate);

        printMortgage(principal, periodMonths, monthlyRate);
        printPaymentsSchedule(principal, periodMonths, monthlyRate);
    }

    private static void printMortgage(int principal, int periodMonths, float monthlyRate) {
        double mortgage = calculateMortgage(principal, monthlyRate, periodMonths);
        System.out.println(" ");
        System.out.println("MORTGAGE");
        System.out.println("--------");
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentsSchedule(int principal, int periodMonths, float monthlyRate) {
        System.out.println(" ");
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short i = 1; i <= periodMonths; i++ ){
            double balance = getBalance(principal, monthlyRate, periodMonths,i);
            String balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println("Balance after " + i + " payments: " + balanceFormatted);
        }
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


    public static int calculatePeriodMonths(int periodYears){
        return periodYears * MONTHS_IN_A_YEAR;
    }


    public static float calculateMonthlyRate(float annualInterestRate){
        float monthlyInterestRate = annualInterestRate / MONTHS_IN_A_YEAR;
        return monthlyInterestRate / PERCENTAGE;
    }


    public static double calculateMortgage(int principal, float ratePercentage, int periodMonth){

        // mortgage formula
        // M = P*[(r*(1+r)^n)/((1+r)^n-1)]
        // M = mortgage
        // P = principal
        // r = monthly interest rate
        // n = period in months

        double calcDivisor = Math.pow(1 + ratePercentage,periodMonth) * ratePercentage;
        double calcDividend = Math.pow(1 + ratePercentage,periodMonth) - 1;
        return principal * calcDivisor / calcDividend;
    }


    public static double getBalance( int principal , float ratePercentage, int periodMonth, int paymentsMade){

        // Remaining loan balance (B) of a fixed payment loan after p months. (https://www.mtgprofessor.com//formulas.htm)
        // B = P*[(1 + r)^n - (1 + r)^p]/[(1 + r)^n - 1]
        // P = principal
        // r = monthly interest rate
        // n = period in months
        // p = payments made

        double calcDivisor = Math.pow(1 + ratePercentage,periodMonth)-Math.pow(1 + ratePercentage,paymentsMade);
        double calcDividend = Math.pow(1 + ratePercentage,periodMonth) - 1;

        double balance = principal * calcDivisor / calcDividend;
        if (balance <= 0) return 0;
        return balance;
    }

}
