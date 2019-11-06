package in.priyanshu.financeCalculator.savings;

import lombok.Builder;
import lombok.Getter;

import java.util.Scanner;

@Getter
@Builder
public class RecurringSavings {
    private Double monthlyAmount;
    private Double interestRate;
    private Double years;

    public Double getFinalAmount() {
        Double finalAmount = 0D;
        for (int year = 0; year < this.years; year++) {
            Double yearsLeft = this.years - year;
            Double savingsForThisYear = getFinalAmountForRecurrentSavingsForAYear(monthlyAmount, interestRate, yearsLeft);
            finalAmount += savingsForThisYear;
        }
        return finalAmount;
    }

    private static Double getFinalAmountForRecurrentSavingsForAYear(
            Double monthlySavings, Double interestRate, Double yearsLeft) {
        double savingsForThisYear = 0D;
        for (int currentMonth = 0; currentMonth < 12 && currentMonth < yearsLeft*12; currentMonth++) {
            Savings savingsForThisMonth = Savings.builder()
                    .amount(monthlySavings)
                    .interestRate(interestRate)
                    .years(yearsLeft)
                    .build();

            Double finalSavingsForThisMonth = savingsForThisMonth.getFinalAmount();
            savingsForThisYear += finalSavingsForThisMonth;
        }
        return savingsForThisYear;
    }
    public static RecurringSavings scanFromInput() {
        Scanner scanner = new Scanner(System.in);
        RecurringSavingsBuilder builder = RecurringSavings.builder();
        System.out.println("Enter Monthly amount saved : ");
        builder.monthlyAmount(scanner.nextDouble());
        System.out.println("Enter InterestRate per annum (1% = 0.01): ");
        builder.interestRate(scanner.nextDouble());
        System.out.println("Enter Duration (number of interest periods): ");
        builder.years(scanner.nextDouble());
        return builder.build();
    }
}
