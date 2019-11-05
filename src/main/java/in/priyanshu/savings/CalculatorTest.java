package in.priyanshu.savings;

import org.junit.jupiter.api.Test;

class CalculatorTest {
  @Test
  void getExpectedNumberOfYearsToReachFIRE_scenario_1() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
        .startingSalaryPerMonth(133000D)
        .startingExpensesPerMonth(90000D)
        .startingSavings(0D)
        .incrementRatePerYear(1.30D)
        .inflationRatePerYear(1.04D)
        .interestRatePerYear(0.065D)
        .yearsLeft(null)
        .build();
    Double yearsLeft = Calculator.getNumberOfYearsToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Years Left : " + yearsLeft);
    assert yearsLeft > 5 && yearsLeft < 6;
  }

  @Test
  void getExpectedNumberOfYearsToReachFIRE_scenario_2() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
        .startingSalaryPerMonth(200000D)
        .startingExpensesPerMonth(93600D)
        .startingSavings(0D)
        .incrementRatePerYear(1.3D)
        .inflationRatePerYear(1.04D)
        .interestRatePerYear(0.07D)
        .yearsLeft(null)
        .build();
    Double yearsLeft = Calculator.getNumberOfYearsToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Years Left : " + yearsLeft);
    assert yearsLeft < 5 && yearsLeft > 4;
  }

  @Test
  void getExpectedNumberOfYearsToReachFIRE_scenario_3() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
        .startingSalaryPerMonth(133000D)
        .startingExpensesPerMonth(90000D)
        .startingSavings(0D)
        .incrementRatePerYear(1.3D)
        .inflationRatePerYear(1.04D)
        .interestRatePerYear(0.07D)
        .yearsLeft(null)
        .build();
    Double yearsLeft = Calculator.getNumberOfYearsToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Years Left : " + yearsLeft);
    assert yearsLeft < 6 && yearsLeft > 5;
  }

  @Test
  void getIncrementRequiredToReachFIRE_scenario_1() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
        .startingSalaryPerMonth(200000D)
        .startingExpensesPerMonth(93600D)
        .startingSavings(0D)
        .incrementRatePerYear(null)
        .inflationRatePerYear(1.04D)
        .interestRatePerYear(0.07D)
        .yearsLeft(5D)
        .build();
    Double incrementRatio = Calculator.getIncrementRatioToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Increment Ratio Required : " + incrementRatio);
    assert incrementRatio > 1.2 && incrementRatio < 1.4;
  }

  @Test
  void getIncrementRequiredToReachFIRE_scenario_2() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
        .startingSalaryPerMonth(133000D)
        .startingExpensesPerMonth(90000D)
        .startingSavings(0D)
        .incrementRatePerYear(null)
        .inflationRatePerYear(1.04D)
        .interestRatePerYear(0.065D)
        .yearsLeft(5D)
        .build();
    Double incrementRatio = Calculator.getIncrementRatioToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Increment Ratio Required : " + incrementRatio);
    assert incrementRatio > 1.6 && incrementRatio < 1.7;
  }

  @Test
  void getIncrementRequiredToReachFIRE_scenario_3() {
    FinanceConfig testFinanceConfig = FinanceConfig.builder()
            .startingSalaryPerMonth(133000D)
            .startingExpensesPerMonth(60000D)
            .startingSavings(0D)
            .incrementRatePerYear(null)
            .inflationRatePerYear(1.04D)
            .interestRatePerYear(0.065D)
            .yearsLeft(5D)
            .build();
    Double incrementRatio = Calculator.getIncrementRatioToReachFIRE(testFinanceConfig);
    testFinanceConfig.printConfig();
    System.out.println("Increment Ratio Required : " + incrementRatio);
    assert incrementRatio > 1.38 && incrementRatio < 1.39;
  }
}