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
        int m1 = -m; // Two's complement of m
        int a = 0; // Accumulator
        int q1 = 0; // Previous bit of Q
        int n = Integer.SIZE; // Number of bits

        for (int i = 0; i < n; i++) {
            if ((q & 1) == 1 && q1 == 0) {
                a += m1; // A = A - M
            } else if ((q & 1) == 0 && q1 == 1) {
                a += m; // A = A + M
            }

            // Arithmetic right shift
            q1 = q & 1;
            q >>= 1;
            a >>= 1;

            // If the sign bit of A is set, set the sign bit of Q
            if ((a & (1 << (n - 1))) != 0) {
                q |= (1 << (n - 1));
            }
        }

        System.out.println("Product: " + ((a << n) | q));
    }
}