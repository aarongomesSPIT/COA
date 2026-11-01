import java.util.Scanner;

public class NonRestoringDivision {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Dividend (Q): ");
        long dividend = in.nextLong();
        System.out.print("Enter Divisor (M): ");
        long divisor = in.nextLong();
        in.close();

        // Use long so all unsigned 32-bit values remain positive.
        if (divisor == 0 || dividend < 0 || divisor < 0
                || dividend > 0xFFFFFFFFL || divisor > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Use non-negative 32-bit values and a non-zero divisor.");
        }

        long remainder = 0; // Partial remainder A.
        long quotient = 0; // Quotient Q.
        for (int bit = 31; bit >= 0; bit--) {
            remainder = (remainder << 1) | ((dividend >>> bit) & 1);
            // Non-restoring step: subtract or add according to A's sign.
            if (remainder >= 0) {
                remainder -= divisor;
            } else {
                remainder += divisor;
            }

            if (remainder >= 0) quotient |= 1L << bit;
        }

        if (remainder < 0) {
            remainder += divisor;
        }
        System.out.println("Quotient (Q): " + quotient);
        System.out.println("Remainder (A): " + remainder);
    }
}
