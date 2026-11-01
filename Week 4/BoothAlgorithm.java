import java.util.Scanner;


public class BoothAlgorithm {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the multiplicand: ");
        int m = scanner.nextInt(); // Multiplicand
        System.out.print("Enter the multiplier: ");
        int q = scanner.nextInt(); // Multiplier

        boothAlgo(m, q);
        scanner.close();
    }

    public static void boothAlgo(int m, int q) {
        int a = 0; // 32-bit accumulator; overflow is the required two's-complement behavior.
        int qMinusOne = 0; // Q(-1), the bit adjacent to Q0.
        int multiplicand = m;

        for (int i = 0; i < Integer.SIZE; i++) {
            int q0 = q & 1;
            if (q0 == 1 && qMinusOne == 0) {
                a -= multiplicand; // 10: A = A - M.
            } else if (q0 == 0 && qMinusOne == 1) {
                a += multiplicand; // 01: A = A + M.
            }

            qMinusOne = q0;
            q = (q >>> 1) | ((int) (a & 1) << 31); // Shift [A,Q,Q(-1)] right.
            a >>= 1; // Arithmetic shift preserves A's sign.
        }

        long product = ((long) a << Integer.SIZE) | (q & 0xFFFFFFFFL);
        System.out.println("Product: " + product);
    }
}