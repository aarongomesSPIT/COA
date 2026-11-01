import java.util.Scanner;


public class BoothAlgorithm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the multiplicand: ");
        int multiplicand = in.nextInt();
        System.out.print("Enter the multiplier: ");
        int multiplier = in.nextInt();
        booth(multiplicand, multiplier);
        in.close();
    }

    // Booth uses Q0 and Q-1 to decide whether to add or subtract M.
    private static void booth(int m, int q) {
        int a = 0; // Accumulator A.
        int qMinusOne = 0; // Previous multiplier bit.
        for (int i = 0; i < Integer.SIZE; i++) {
            int q0 = q & 1; // Current multiplier bit.
            if (q0 == 1 && qMinusOne == 0) {
                a -= m; // 10: A = A - M.
            } else if (q0 == 0 && qMinusOne == 1) {
                a += m; // 01: A = A + M.
            }

            qMinusOne = q0;
            // Arithmetic right shift of the combined [A][Q][Q-1] register.
            q = (q >>> 1) | ((a & 1) << 31);
            a >>= 1;
        }

        // The 64-bit product is the final [A][Q] pair.
        long product = ((long) a << Integer.SIZE) | (q & 0xFFFFFFFFL);
        System.out.println("Product: " + product);
    }
}