#include <stdio.h>

int main() {
    int M, Q;
    int A = 0, Q_1 = 0;
    int count = 32; // Since 32 bit

    printf("Enter Multiplicand (M): ");
    scanf("%d", &M);
    printf("Enter Multiplier (Q): ");
    scanf("%d", &Q);

    printf("\nInitial State: A = 0, Q = %d, Q_-1 = 0\n", Q);
    printf("-----------------------------------------\n");

    while (count > 0) {
        int Q_0 = Q & 1; // Gets Q0 as a number 001 & 1 reslts in the last bit getting anded

        // 1. Check operations based on Q_0 and Q_1
        if (Q_0 == 1 && Q_1 == 0) {
            A = A - M;
        } else if (Q_0 == 0 && Q_1 == 1) {
            A = A + M;
        }

        // 2. Arithmetic Right Shift for combined [A][Q][Q_1]
        Q_1 = Q & 1; // Moves the last bit from Q to Q-1
        
        // Shift Q right, bringing in A's LSB at the sign position
        Q = (Q >> 1) & ~(1 << 31); // shift Q 1 to the right and make the 1st bit 0
        Q |= (A & 1) << 31;        // Gets the 31st bit of A into Q
                                   // real 8-bit registers are better handled here,
                                   // but shifting A and Q together is shown below.

        // Shift A right arithmetically (preserves sign bit)
        A = A >> 1;

        count--;
    }

    // Combine A and Q into the final product
    // (Assuming standard 8-bit multiplication results in a 16-bit output)
    short final_product = (A << 8) | (Q & 0xFF);

    printf("Decimal Product Result: %d\n", final_product);

    return 0;
}