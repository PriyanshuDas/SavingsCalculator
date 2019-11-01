package in.priyanshu.savings;

import java.util.Scanner;

public class FinanceHelper {

  private static double getCumulativeValueForInterestRate(double amount, double interestRate,
      double period) {
    double currentValue = amount;
    for (int i = 0; i < period; i++) {
      currentValue = currentValue * (1 + interestRate);
    }
    return currentValue;
  }

  static void getFinalValueForLumpDeposit() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Starting amount : ");
    Double amount = scanner.nextDouble();
    System.out.println("Enter InterestRate (1% = 0.01): ");
    Double interestRate = scanner.nextDouble();
    System.out.println("Enter Duration (number of interest periods): ");
    Double period = scanner.nextDouble();
    Double finalValue = getCumulativeValueForInterestRate(amount, interestRate, period);
    System.out.println("The final value saved is : " + finalValue);
  }

  static void getValueForFixedAmountRecurringSavings() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter amount saved per month: ");
    Double monthlySavings = scanner.nextDouble();
    System.out.println("Enter InterestRate (1% = 0.01): ");
    Double interestRate = scanner.nextDouble();
    System.out.println("Enter Duration (in years): ");
    Double periodInYears = scanner.nextDouble();
    Double finalValue = getCumulativeValueForMonthlySavings(monthlySavings, interestRate,
        periodInYears);
    System.out.println("The final value saved is : " + finalValue);
  }

  private static Double getCumulativeValueForMonthlySavings(Double monthlySavings,
      Double interestRate, Double periodInYears) {
    Double totalMonths = periodInYears * 12;
    Double finalValue = 0D;
    for (int year = 0; year < periodInYears; year++) {
      Double monthsLeft = totalMonths - year*12;
      Double savingsForThisYear = getSavingsOnRecurringMonthlyDepositsOverAYearForDurationOfMonths(
          monthlySavings, interestRate, monthsLeft);
      finalValue += savingsForThisYear;
    }
    return finalValue;
  }

  static Double getSavingsOnRecurringMonthlyDepositsOverAYearForDurationOfMonths(
      Double monthlySavings, Double interestRate, Double monthsLeft) {
    double savingsForThisYear = 0D;
    for (int currentMonth = 0; currentMonth < 12 && currentMonth < monthsLeft; currentMonth++) {
      savingsForThisYear += getCumulativeValueForInterestRate(monthlySavings, interestRate,
          (monthsLeft - currentMonth) / 12);
    }
    return savingsForThisYear;
  }
}
