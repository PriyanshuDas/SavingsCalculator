package in.priyanshu.financeCalculator.savings;

import lombok.Builder;
import lombok.Getter;

import java.util.Scanner;

@Getter
@Builder
public class Savings {
    private Double amount;
    private Double interestRate;
    private Double years;

    public double getFinalAmount() {
        double currentValue = amount;
        for (int i = 0; i < years; i++) {
            currentValue = currentValue * (1 + interestRate);
        }
        return currentValue;
    }

    public static Savings scanFromInput() {
        Scanner scanner = new Scanner(System.in);
        SavingsBuilder builder = Savings.builder();
        System.out.println("Enter Starting amount : ");
        builder.amount(scanner.nextDouble());
        System.out.println("Enter InterestRate (1% = 0.01): ");
        builder.interestRate(scanner.nextDouble());
        System.out.println("Enter Duration (number of interest periods): ");
       builder.years(scanner.nextDouble());
       return builder.build();
    }
}
