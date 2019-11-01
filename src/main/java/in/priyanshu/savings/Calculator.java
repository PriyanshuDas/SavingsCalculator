package in.priyanshu.savings;

import java.util.Scanner;

/*
  Todo:
    - Add a parameter for expected expense bumps (marriage, kids, etc.)
    - Add a parameter for defining an extra "emergency fund"
    - Make the code more "modular"
 */

public class Calculator {

  private static double getCumulativeValueForInterestRate(double amount, double interestRate,
      double period) {
    double currentValue = amount;
    for (int i = 0; i < period; i++) {
      currentValue = currentValue * (1 + interestRate);
    }
    return currentValue;
  }

  public static void main(String[] args) {
    interactiveSavingsCalculator();
  }

  private static void interactiveSavingsCalculator() {
    Scanner scanner = new Scanner(System.in);
    printOptions();
    int option = scanner.nextInt();
    switch (option) {
      case 1:
        getFinalValueForLumpDeposit();
        break;
      case 2:
        getValueForFixedAmountRecurringSavings();
        break;
      case 3:
        getValueBasedOnPercentageOfIncomeSavedAccountingForIncreaseInIncome();
        break;
      case 4:
        getValueAccountingForAllScenarios();
        break;
      case 5:
        getExpectedNumberOfYearsToReachFIRE();
        break;
      case 6:
        getExpectedIncrementRatioToReachFIRE();
        break;
      default:
        System.out.println("Invalid option selected, exiting");
    }
  }

  //todo: fix
  private static void getExpectedIncrementRatioToReachFIRE() {
  }

  private static void getExpectedNumberOfYearsToReachFIRE() {
    FinanceConfig financeConfig = FinanceConfig.scanFinanceConfig(
        true,
        true,
        true,
        true,
        true,
        false);

    YearBinarySearch yearBinarySearch = YearBinarySearch.builder()
        .financeConfig(financeConfig)
        .build();

    Double yearsRequired = yearBinarySearch.findValue(0D, 10000D);
    System.out.println("Years Required is : " + yearsRequired);
//    Scanner scanner = new Scanner(System.in);
//    System.out.println("Enter current salary (monthly): ");
//    Double startingSalary = scanner.nextDouble();
//    System.out.println("Enter expected increase in income per year (40% = 0.4): ");
//    Double expectedIncrease = scanner.nextDouble();
//    System.out.println("Enter current monthly expenses (including personal, assume rest is saved) : ");
//    Double startingExpenses = scanner.nextDouble();
//    System.out.println("Enter Inflation rate (1% = 0.01): ");
//    Double inflationRate = scanner.nextDouble();
//    System.out.println("Enter InterestRate (1% = 0.01): ");
//    Double interestRate = scanner.nextDouble();
//
//
//    Double finalSavings = 0D;
//    Double currentSalary = startingSalary;
//    Double currentExpenses = startingExpenses;
//    int numberOfYears = 0;
//    while(currentExpenses*12 > finalSavings*interestRate) {
//      double monthlySavings = (currentSalary - currentExpenses);
//      finalSavings += finalSavings*(1+interestRate);
//      finalSavings += monthlySavings*12;
//      currentSalary = currentSalary * (1 + expectedIncrease);
//      currentExpenses = currentExpenses* (1 + inflationRate);
//      numberOfYears++;
//    }
//    System.out.println("The number of years required is : " + numberOfYears);
  }

  private static void getValueAccountingForAllScenarios() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter current salary (monthly): ");
    Double startingSalary = scanner.nextDouble();
    System.out.println("Enter expected increase in income per year (40% = 0.4): ");
    Double expectedIncrease = scanner.nextDouble();
    System.out.println("Enter current monthly expenses (including personal, assume rest is saved) : ");
    Double startingExpenses = scanner.nextDouble();
    System.out.println("Enter Inflation rate (1% = 0.01): ");
    Double inflationRate = scanner.nextDouble();
    System.out.println("Enter InterestRate (1% = 0.01): ");
    Double interestRate = scanner.nextDouble();
    System.out.println("Enter Duration (in years): ");
    Double periodInYears = scanner.nextDouble();

    Double finalSavings = 0D;
    Double currentSalary = startingSalary;
    Double currentExpenses = startingExpenses;
    for (int currentYear = 0; currentYear < periodInYears; currentYear++) {
      Double monthsLeft = periodInYears*12 - currentYear*12;
      double monthlySavings = (currentSalary - currentExpenses);
      Double cumulativeValueForYear = getSavingsOnRecurringMonthlyDepositsOverAYearForDurationOfMonths(
          monthlySavings, interestRate, monthsLeft);
      finalSavings += cumulativeValueForYear;
      currentSalary = currentSalary * (1 + expectedIncrease);
      currentExpenses = currentExpenses* (1 + inflationRate);
    }
    System.out.println("The final value saved is : " + finalSavings);
  }

  private static void printOptions() {
    System.out.println("Select an option: ");
    System.out.println("1. Get Final Value for a lump deposit");
    System.out.println("2. Get Final Value for monthly recurring savings");
    System.out.println("3. Get Value based on percentage of income, accounting for increase in income");
    System.out.println("4. Get Value accounting for expenditure, inflation, savings and increase in income");
    System.out.println("5. Get Expected number of years to reach FIRE");
    System.out.println("6. Get Expected salary increment to reach FIRE");
  }

  private static void getValueBasedOnPercentageOfIncomeSavedAccountingForIncreaseInIncome() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter current salary (monthly): ");
    Double startingSalary = scanner.nextDouble();
    System.out.println("Enter ratio of income saved per month (50% is 0.5): ");
    Double savingRatio = scanner.nextDouble();
    System.out.println("Enter InterestRate (1% = 0.01): ");
    Double interestRate = scanner.nextDouble();
    System.out.println("Enter Duration (in years): ");
    Double periodInYears = scanner.nextDouble();
    System.out.println("Enter expected increase in income per year (40% = 0.4): ");
    Double expectedIncrease = scanner.nextDouble();

    Double finalSavings = 0D;

    Double currentSalary = startingSalary;
    for (int currentYear = 0; currentYear < periodInYears; currentYear++) {
      Double monthsLeft = periodInYears*12 - currentYear*12;
      double monthlySavings = currentSalary * savingRatio;
      Double cumulativeValueForYear = getSavingsOnRecurringMonthlyDepositsOverAYearForDurationOfMonths(
          monthlySavings, interestRate, monthsLeft);
      finalSavings += cumulativeValueForYear;
      currentSalary = currentSalary * (1 + expectedIncrease);
    }
    System.out.printf("The final value saved is : %10.2f\n" + finalSavings);
  }

  private static void getFinalValueForLumpDeposit() {
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

  private static void getValueForFixedAmountRecurringSavings() {
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

  private static Double getSavingsOnRecurringMonthlyDepositsOverAYearForDurationOfMonths(
      Double monthlySavings, Double interestRate, Double monthsLeft) {
    double savingsForThisYear = 0D;
    for (int currentMonth = 0; currentMonth < 12 && currentMonth < monthsLeft; currentMonth++) {
      savingsForThisYear += getCumulativeValueForInterestRate(monthlySavings, interestRate,
          (monthsLeft - currentMonth) / 12);
    }
    return savingsForThisYear;
  }
}
