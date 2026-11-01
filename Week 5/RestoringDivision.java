import java.util.Scanner;

public class RestoringDivision {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Dividend (Q): ");
        long dividend = in.nextLong();
        System.out.print("Enter Divisor (M): ");
        long divisor = in.nextLong();
        in.close();

        // The algorithm below handles unsigned 32-bit input stored in long.
        if (divisor == 0 || dividend < 0 || divisor < 0
                || dividend > 0xFFFFFFFFL || divisor > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Use non-negative 32-bit values and a non-zero divisor.");
        }

        long quotient = 0; // Q
        long remainder = 0; // A
        for (int bit = 31; bit >= 0; bit--) {
            // Shift the next dividend bit into the partial remainder.
            remainder = (remainder << 1) | ((dividend >>> bit) & 1);
            if (remainder >= divisor) {
                remainder -= divisor;       // Keep subtraction.
                quotient |= 1L << bit;      // Quotient bit = 1.
            }
        }

        System.out.println("Quotient (Q): " + quotient);
        System.out.println("Remainder (A): " + remainder);
    }
}
