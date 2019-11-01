package in.priyanshu.savings;

import java.util.Scanner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FinanceConfig {
  Double startingSavings;
  Double startingExpenses;
  Double startingSalary;
  Double incrementRate;
  Double inflationRate;
  Double yearsLeft;

  public static FinanceConfig scanFinanceConfig(boolean startingSavingsEnabled, boolean startingExpensesEnabled,
      boolean startingSalaryEnabled, boolean incrementRateEnabled, boolean inflationRateEnabled,
      boolean yearsLeftEnabled) {
    Scanner scanner = new Scanner(System.in);
    FinanceConfigBuilder builder = FinanceConfig.builder();
    if (startingSavingsEnabled) {
      System.out.println("Enter starting Savings : ");
      builder.startingSavings(scanner.nextDouble());
    }
    if (startingExpensesEnabled) {
      System.out.println("Enter starting Expenses : ");
      builder.startingExpenses(scanner.nextDouble());
    }
    if (startingSalaryEnabled) {
      System.out.println("Enter starting Salary : ");
      builder.startingSalary(scanner.nextDouble());
    }
    if (incrementRateEnabled) {
      System.out.println("Enter increment Rate : ");
      builder.incrementRate(scanner.nextDouble());
    }
    if (inflationRateEnabled) {
      System.out.println("Enter inflation Rate : ");
      builder.inflationRate(scanner.nextDouble());
    }
    if (yearsLeftEnabled) {
      System.out.println("Enter years Left : ");
      builder.yearsLeft(scanner.nextDouble());
    }
    return builder.build();
  }
}