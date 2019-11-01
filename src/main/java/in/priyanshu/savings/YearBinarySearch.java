package in.priyanshu.savings;

import in.priyanshu.binarySearch.BinarySearch;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class YearBinarySearch extends BinarySearch {
  FinanceConfig financeConfig;
  /*
    finalSavings*interestRate = 12*currentExpenses*(inflationRate^years)
    finalSavings = 12*currentExpenses*(inflation^years)/interestRate
    finalSavings = 12*Sum{0, years}(startingSalary*(increment^i) - currentExpenses*(inflation^i))
     = 12*startingSalary*(increment^(years+1) - 1)/(increment - 1) - 12*currentExpenses*(inflation^(years+1) - 1)/(inflation - 1))
     => Reduce equation
     12*(startingSalary*(increment^(years+1) - 1)/(increment - 1) -
      currentExpenses*(inflation^(years+1) - 1)/(inflation - 1)) == 0
      left < 0 right > 0, center == 0
  */

  public SearchRange getNewSearchRange(Double year) {
    Double leftValue =
        12*financeConfig.getStartingSalary()
            * (Math.pow(financeConfig.getIncrementRate(), year + 1) - 1)
            / (financeConfig.getIncrementRate() - 1);

    Double rightValue =
        12*financeConfig.getStartingExpenses()
        * (Math.pow(financeConfig.getInflationRate(), year + 1) - 1)
        / (financeConfig.getInflationRate() - 1);

    if (leftValue < rightValue) return SearchRange.RIGHT;
    else if (leftValue.equals(rightValue)) return SearchRange.MIDDLE;
    else if (leftValue > rightValue) return SearchRange.LEFT;
    else return SearchRange.NONE;
  }
}
