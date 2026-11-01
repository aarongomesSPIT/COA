import java.util.Scanner;

public class NonRestoringDivision {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Dividend (Q): ");
        int Q = in.nextInt(); // Dividend.
        System.out.print("Enter Divisor (M): ");
        int M = in.nextInt(); // Divisor.
        in.close();

        int A = 0; // Partial remainder.
        int count = 32; // Number of bits.
        while (count-- > 0) {
            // Shift the next dividend bit into A.
            A = (A << 1) | ((Q >>> 31) & 1);
            Q <<= 1;

            // Subtract or add the divisor based on the sign of A.
            if (A >= 0) {
                A -= M; // A = A - M.
            } else {
                A += M; // A = A + M.
            }

            // A >= 0 means Q0 is 1; otherwise Q0 is 0.
            if (A >= 0) {
                Q |= 1;
            } else {
                Q &= ~1;
            }
        }

        if (A < 0) {
            A += M; // Final correction if A is negative.
        }
        System.out.println("Quotient (Q): " + Q);
        System.out.println("Remainder (A): " + A);
    }
}
