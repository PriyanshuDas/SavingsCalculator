package in.priyanshu.savings.variableFinders;

import in.priyanshu.binarySearch.BinarySearch;
import in.priyanshu.savings.FinanceConfig;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class IncrementBinarySearch extends BinarySearch {
  FinanceConfig financeConfig;
  protected static final double DELTA = 0.00001;

  public SearchRange getNewSearchRange(Double incrementRate) {
    FinanceConfig currentFinanceConfig = FinanceConfig.from(financeConfig);
    currentFinanceConfig.setIncrementRatePerYear(incrementRate);

    Double savingsDeficit =
        currentFinanceConfig.getSavingsDeficitInFinalYear();

    if (savingsDeficit > 0) return SearchRange.RIGHT;
    else if (Math.abs(savingsDeficit) <= DELTA) return SearchRange.MIDDLE;
    else if (savingsDeficit < 0) return SearchRange.LEFT;
    else return SearchRange.NONE;
  }
}
