package in.priyanshu.savings.financeConfig;

import lombok.Builder;
import lombok.Getter;

import java.util.Scanner;

@Getter
@Builder
public
class ChildExpensesConfig implements OptionalExpenseConfig {
    ExpenseConfig expenseConfig;
    Integer yearsFromNow;

    public static ChildExpensesConfig scanFromInput() {
        Scanner scanner = new Scanner(System.in);
        ChildExpensesConfigBuilder builder = ChildExpensesConfig.builder();
        System.out.println("Taking Input for Post-Child Expense Configuration");
        ExpenseConfig expenseConfig = ExpenseConfig.scanExpenseConfig();
        builder.expenseConfig(expenseConfig);
        if (expenseConfig.getEnabled()) {
            System.out.println("How many years from now is child expenses expected to start?");
            builder.yearsFromNow(scanner.nextInt());
        }
        return builder.build();
    }

    public boolean isEnabled() {
        return this.expenseConfig != null && this.expenseConfig.getEnabled();
    }
}
