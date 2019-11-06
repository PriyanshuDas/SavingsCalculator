package in.priyanshu.financeCalculator.expenseConfigs;

import lombok.Builder;
import lombok.Getter;

import java.util.Scanner;

@Getter
@Builder
public
class MarriageExpensesConfig implements OptionalExpenseConfig {
    ExpenseConfig expenseConfig;
    Integer yearsFromNow;

    public static MarriageExpensesConfig scanFromInput() {
        Scanner scanner = new Scanner(System.in);
        MarriageExpensesConfigBuilder builder = MarriageExpensesConfig.builder();
        System.out.println("Taking Input for Post-Marriage Configuration");
        ExpenseConfig expenseConfig = ExpenseConfig.scanExpenseConfig();
        builder.expenseConfig(expenseConfig);
        if (expenseConfig.getEnabled()) {
            System.out.println("How many years from now is marriage expenses expected to start?");
            builder.yearsFromNow(scanner.nextInt());
        }
        return builder.build();
    }

    public boolean isEnabled() {
        return this.expenseConfig != null && this.expenseConfig.getEnabled();
    }
}
