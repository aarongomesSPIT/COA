import java.util.Scanner;

public class RestoringDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Dividend (Q): ");
        long dividend = scanner.nextLong();
        System.out.print("Enter Divisor (M): ");
        long divisor = scanner.nextLong();
        scanner.close();

        if (divisor == 0 || dividend < 0 || divisor < 0
                || dividend > 0xFFFFFFFFL || divisor > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Use non-negative 32-bit values and a non-zero divisor.");
        }

        long quotient = 0;
        long remainder = 0;
        for (int bit = 31; bit >= 0; bit--) {
            remainder = (remainder << 1) | ((dividend >>> bit) & 1);
            if (remainder >= divisor) {
                remainder -= divisor;
                quotient |= 1L << bit;
            }
        }

        System.out.println("Quotient (Q): " + quotient);
        System.out.println("Remainder (A): " + remainder);
    }
}
