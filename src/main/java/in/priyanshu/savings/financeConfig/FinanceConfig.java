package in.priyanshu.savings.financeConfig;

import java.util.Scanner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


//todo: Verify year, year-1 interest calculation related issues

@Getter
@Setter
@Builder
public class FinanceConfig {
  Double startingSavings;
  Double startingExpensesPerMonth;
  Double startingSalaryPerMonth;
  Double incrementRatePerYear;
  Double inflationRatePerYear;
  Double interestRatePerYear;
  Double yearsLeft;
  Boolean emergencyFundEnabled;
  EmergencyFundConfig emergencyFundConfig;
  Boolean marriageExpensesEnabled;
  MarriageExpensesConfig marriageExpensesConfig;
  Boolean childExpensesEnabled;
  ChildExpensesConfig childExpensesConfig;

  // scanners
  public static FinanceConfig scanFinanceConfig(boolean startingSavingsEnabled, boolean startingExpensesEnabled,
                                                boolean startingSalaryEnabled, boolean incrementRateEnabled, boolean inflationRateEnabled,
                                                boolean interestRateEnabled, boolean yearsLeftEnabled) {
    Scanner scanner = new Scanner(System.in);
    FinanceConfigBuilder builder = FinanceConfig.builder();
    scanStartingSavings(startingSavingsEnabled, scanner, builder);
    scanStartingExpenses(startingExpensesEnabled, scanner, builder);
    scanStartingSalary(startingSalaryEnabled, scanner, builder);
    scanIncrementRate(incrementRateEnabled, scanner, builder);
    scanInflationRate(inflationRateEnabled, scanner, builder);
    scanInterestRate(interestRateEnabled, scanner, builder);
    scanYearsLeft(yearsLeftEnabled, scanner, builder);
    scanEmergencyFundConfig(builder);
    scanMarriageExpenseConfig(builder);
    scanChildExpenseConfig(builder);
    return builder.build();
  }

  private static void scanChildExpenseConfig(FinanceConfigBuilder builder) {
    builder.childExpensesConfig(ChildExpensesConfig.scanFromInput());
  }

  private static void scanMarriageExpenseConfig(FinanceConfigBuilder builder) {
    builder.marriageExpensesConfig(MarriageExpensesConfig.scanFromInput());
  }

  private static void scanEmergencyFundConfig(FinanceConfigBuilder builder) {
    builder.emergencyFundConfig(EmergencyFundConfig.scanFromInput());
  }

  private static void scanYearsLeft(boolean yearsLeftEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (yearsLeftEnabled) {
      System.out.println("Enter years Left : ");
      builder.yearsLeft(scanner.nextDouble());
    }
  }

  private static void scanInterestRate(boolean interestRateEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (interestRateEnabled) {
      System.out.println("Enter interest Rate (7% interest = 0.07) : ");
      builder.interestRatePerYear(scanner.nextDouble());
    }
  }

  private static void scanInflationRate(boolean inflationRateEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (inflationRateEnabled) {
      System.out.println("Enter inflation Rate (4% inflation = 1.04) : ");
      builder.inflationRatePerYear(scanner.nextDouble());
    }
  }

  private static void scanIncrementRate(boolean incrementRateEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (incrementRateEnabled) {
      System.out.println("Enter increment Rate (30% increase = 1.30) : ");
      builder.incrementRatePerYear(scanner.nextDouble());
    }
  }

  private static void scanStartingSalary(boolean startingSalaryEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (startingSalaryEnabled) {
      System.out.println("Enter starting Salary : ");
      builder.startingSalaryPerMonth(scanner.nextDouble());
    }
  }

  private static void scanStartingExpenses(boolean startingExpensesEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (startingExpensesEnabled) {
      System.out.println("Enter starting Expenses : ");
      builder.startingExpensesPerMonth(scanner.nextDouble());
    }
  }

  private static void scanStartingSavings(boolean startingSavingsEnabled, Scanner scanner, FinanceConfigBuilder builder) {
    if (startingSavingsEnabled) {
      System.out.println("Enter starting Savings : ");
      builder.startingSavings(scanner.nextDouble());
    }
  }

  //getters

  private Double getSavingsAtYear(Double year) {
    Double totalSavings = this.startingSavings;

    //todo: Find a more efficient way to calculate this!
    for(int currentYear = 0; currentYear < year; currentYear++) {
      Double incomeForCurrentYear =
          this.getStartingSalaryPerYear()*Math.pow(incrementRatePerYear, currentYear);
      Double expensesForCurrentYear =
          this.getStartingExpensesPerYear()*Math.pow(inflationRatePerYear, currentYear);
      Double savingsForThisYear = incomeForCurrentYear - expensesForCurrentYear;
      Double savingsScaled =
          savingsForThisYear*Math.pow(this.getInterestRatePerYear(), year - currentYear);
      totalSavings += (savingsForThisYear + savingsScaled);
    }

    return totalSavings;
  }

  private Double getExpensesInYear(Double year) {
    return 12*this.getStartingExpensesPerMonth()*Math.pow(this.getInflationRatePerYear(), year);
  }

  private Double getStartingExpensesPerYear() {
    return 12*this.getStartingExpensesPerMonth();
  }

  private Double getStartingSalaryPerYear() {
    return 12*this.getStartingSalaryPerMonth();
  }

  public Double getSavingsDeficitInFinalYear() {
    Double totalExpensesInYear = getExpensesInFinalYear();
    Double finalSavings = getSavingsInFinalYear();
    Double interestOnFinalSavings = finalSavings*this.getInterestRatePerYear();
    return totalExpensesInYear - interestOnFinalSavings;
  }

  public Double getSavingsInFinalYear() {
    return getSavingsAtYear(this.getYearsLeft());
  }

  private Double getExpensesInFinalYear() {
    return getExpensesInYear(this.getYearsLeft());
  }

  // misc

  public static FinanceConfig from(FinanceConfig financeConfig) {
    return FinanceConfig.builder()
            .startingSavings(financeConfig.getStartingSavings())
            .startingExpensesPerMonth(financeConfig.getStartingExpensesPerMonth())
            .startingSalaryPerMonth(financeConfig.getStartingSalaryPerMonth())
            .incrementRatePerYear(financeConfig.getIncrementRatePerYear())
            .inflationRatePerYear(financeConfig.getInflationRatePerYear())
            .interestRatePerYear(financeConfig.getInterestRatePerYear())
            .yearsLeft(financeConfig.getYearsLeft())
            .build();
  }

  public void printConfig() {
    System.out.println("startingSavings : " + startingSavings);
    System.out.println("startingExpensesPerMonth : " + startingExpensesPerMonth);
    System.out.println("startingSalaryPerMonth : " + startingSalaryPerMonth);
    System.out.println("incrementRatePerYear : " + incrementRatePerYear);
    System.out.println("inflationRatePerYear : " + inflationRatePerYear);
    System.out.println("interestRatePerYear : " + interestRatePerYear);
    System.out.println("yearsLeft : " + yearsLeft);
  }

}