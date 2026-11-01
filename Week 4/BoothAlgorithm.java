import java.util.Scanner;
public class BoothAlgorithm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the multiplicand: ");
        int M = in.nextInt(); // M: multiplicand.
        System.out.print("Enter the multiplier: ");
        int Q = in.nextInt(); // Q: multiplier.
        booth(M, Q);
        in.close();
    }

    // Q0 and Q-1 decide whether to add or subtract M.
    private static void booth(int M, int Q) {
        int A = 0; // Accumulator A.
        int Q_1 = 0; // Previous bit of Q, initially 0.
        int count = 32; // Number of bits.
        while (count-- > 0) {
            int Q0 = Q & 1; // Read the current least-significant bit of Q.
            if (Q0 == 1 && Q_1 == 0) {
                A -= M; // Pair 10: A = A - M.
            } else if (Q0 == 0 && Q_1 == 1) {
                A += M; // Pair 01: A = A + M.
            }

            Q_1 = Q0; // Save Q0 as the next Q-1.
            Q = (Q >>> 1) | ((A & 1) << 31); // Shift the combined A-Q register.
            A >>= 1; // Arithmetic shift keeps the sign of A.
        }

        System.out.println("Product: " + Q); // Q contains the 32-bit result.
    }
}