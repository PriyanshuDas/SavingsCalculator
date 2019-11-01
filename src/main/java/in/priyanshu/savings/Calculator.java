package in.priyanshu.savings;

import in.priyanshu.savings.variableFinders.IncrementBinarySearch;
import in.priyanshu.savings.variableFinders.YearBinarySearch;
import java.util.Scanner;

/*
  Todo:
    - Add a parameter for expected expense bumps (marriage, kids, etc.)
    - Add a parameter for defining an extra "emergency fund"
    - Make the code more "modular"
 */

public class Calculator {

  public static void main(String[] args) {
    interactiveSavingsCalculator();
  }

  private static void printOptions() {
    System.out.println("Select an option: ");
    System.out.println("1. Get Final Value for a lump deposit");
    System.out.println("2. Get Final Value for monthly recurring savings");
    System.out.println("3. Get Savings accounting for expenditure, inflation, savings and increase in income");
    System.out.println("4. Get Expected number of years to reach FIRE");
    System.out.println("5. Get Expected salary increment to reach FIRE");
    System.out.println("6. Quit");
  }

  private static void interactiveSavingsCalculator() {
    Scanner scanner = new Scanner(System.in);
    boolean repeatLoop = true;
    while(repeatLoop) {
      printOptions();
      int option = scanner.nextInt();
      switch (option) {
        case 1:
          FinanceHelper.getFinalValueForLumpDeposit();
          break;
        case 2:
          FinanceHelper.getValueForFixedAmountRecurringSavings();
          break;
        case 3:
          getValueAccountingForAllScenarios();
          break;
        case 4:
          processInputForExpectedNumberOfYearsToReachFIRE();
          break;
        case 5:
          processInputForExpectedIncrementRatioToReachFIRE();
          break;
        case 6:
          repeatLoop = false;
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid option selected, exiting");
      }
    }
  }

  //todo: Move to input class
  private static void processInputForExpectedNumberOfYearsToReachFIRE() {
    FinanceConfig financeConfig = FinanceConfig.scanFinanceConfig(
        true,
        true,
        true,
        true,
        true,
        true,
        false);

    Double yearsRequired = getNumberOfYearsToReachFIRE(financeConfig);
    System.out.println("Years Required is : " + yearsRequired);
  }

  //todo: fix
  private static void processInputForExpectedIncrementRatioToReachFIRE() {
    FinanceConfig financeConfig = FinanceConfig.scanFinanceConfig(
        true,
        true,
        true,
        false,
        true,
        true,
        true);

    Double yearsRequired = getIncrementRatioToReachFIRE(financeConfig);
    System.out.println("Years Required is : " + yearsRequired);
  }

  static Double getIncrementRatioToReachFIRE(FinanceConfig financeConfig) {
    IncrementBinarySearch incrementBinarySearch = IncrementBinarySearch.builder()
        .financeConfig(financeConfig)
        .build();
    return incrementBinarySearch.findValue(0D, 5D);
  }

  static Double getNumberOfYearsToReachFIRE(FinanceConfig financeConfig) {
    YearBinarySearch yearBinarySearch = YearBinarySearch.builder()
        .financeConfig(financeConfig)
        .build();
    return yearBinarySearch.findValue(0D, 100D);
  }

  private static void getValueAccountingForAllScenarios() {
    FinanceConfig financeConfig = FinanceConfig.scanFinanceConfig(
        true,
        true,
        true,
        true,
        true,
        true,
        true);

    Double finalSavings = financeConfig.getSavingsInFinalYear();
    System.out.println("The final value saved is : " + finalSavings);
  }

}
