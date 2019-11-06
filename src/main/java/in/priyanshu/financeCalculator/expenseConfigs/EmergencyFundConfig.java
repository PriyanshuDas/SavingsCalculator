package in.priyanshu.financeCalculator.expenseConfigs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public
class EmergencyFundConfig implements OptionalExpenseConfig {
    ExpenseConfig expenseConfig;

    public static EmergencyFundConfig scanFromInput() {
        System.out.println("Taking Input for Emergency Fund Configuration");
        return EmergencyFundConfig.builder()
                .expenseConfig(ExpenseConfig.scanExpenseConfig())
                .build();
    }

    public boolean isEnabled() {
        return this.expenseConfig != null && this.expenseConfig.getEnabled();
    }
}
