import java.util.Scanner;

public class RestoringDivision {
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
            // Shift the next dividend bit into the partial remainder.
            A = (A << 1) | ((Q >>> 31) & 1);
            Q <<= 1;
            A -= M;
            if (A < 0) {
                A += M; // Restore A after an unsuccessful subtraction.
            } else {
                Q |= 1; // Successful subtraction sets Q0 to 1.
            }
        }

        System.out.println("Quotient (Q): " + Q);
        System.out.println("Remainder (A): " + A);
    }
}
