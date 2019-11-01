package in.priyanshu.savings.variableFinders;

import in.priyanshu.binarySearch.BinarySearch;
import in.priyanshu.savings.FinanceConfig;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class YearBinarySearch extends BinarySearch {
  FinanceConfig financeConfig;
  protected static final double DELTA = 0.001;

  public SearchRange getNewSearchRange(Double year) {
    FinanceConfig newFinanceConfig = FinanceConfig.from(financeConfig);
    newFinanceConfig.setYearsLeft(year);
    Double expenseDeficit = newFinanceConfig.getSavingsDeficitInFinalYear();
    if (expenseDeficit > DELTA) return SearchRange.RIGHT;
    else if (expenseDeficit <= DELTA && expenseDeficit >= 0) return SearchRange.MIDDLE;
    else if (expenseDeficit < 0) return SearchRange.LEFT;
    else return SearchRange.NONE;
  }
}
