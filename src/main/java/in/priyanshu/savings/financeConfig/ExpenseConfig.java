package in.priyanshu.savings.financeConfig;

import lombok.Builder;
import lombok.Getter;

import java.util.Scanner;

@Getter
@Builder
class ExpenseConfig {
    Boolean enabled;
    EmergencyFundType emergencyFundType;
    Double expenseMultiplier;
    Double flatAmount;

    enum EmergencyFundType {
        FLAT_VALUE,
        MULTIPLIER
    }

    static ExpenseConfig scanExpenseConfig() {
        Scanner scanner = new Scanner(System.in);
        ExpenseConfigBuilder builder = ExpenseConfig.builder();
        System.out.println("Enabled : (0: No, 1: Yes) ");
        if(scanner.nextInt() != 0) {
            builder.enabled(true);
            System.out.println("Expense is defined as a (1: Flat Value (at current rate), 2: Multiplier (of starting expenses) ): ");
            int fundTypeInput = scanner.nextInt();
            if(fundTypeInput == 2) {
                builder.emergencyFundType(EmergencyFundType.MULTIPLIER);
                System.out.println("Enter Multiplier (1.5 means 1.5x of monthly expenses would be used)");
                builder.expenseMultiplier(scanner.nextDouble());
            }
            else {
                if(fundTypeInput != 1) {
                    System.out.println("Invalid Input, assuming Flat Value");
                }
                builder.emergencyFundType(EmergencyFundType.FLAT_VALUE);
                System.out.println("Enter flat amount (at starting year)");
                builder.flatAmount(scanner.nextDouble());
            }
        }
        return builder.build();
    }
}
