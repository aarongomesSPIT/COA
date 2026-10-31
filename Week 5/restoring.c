#include <stdio.h>

int N = 32; // Since 32 bit for int in dividend
unsigned A = 0;
unsigned M, Q;

void display();
int main()
{

    printf("Enter Dividend (Q): ");
    scanf("%u", &Q);
    printf("Enter Divisor (M): ");
    scanf("%u", &M);

    while (N > 0)
    {
        // Shift Left AQ

        // Shift just A (also take the 31st bit of Q and OR it with A's Last bit)
        A = (A << 1) | ((Q >> 31) & 1U);

        // Shift just Q
        Q = Q << 1;

        A = A - M;

        if((A >> 31) & 1U)
        {
            Q &= ~1U;  // Set Q's Last bit as 0
            A = A + M;
        }
        else
        {
            Q |= 1U;  // Sets Q's Last bit as 1
        }
        N--; // Decrement the counter
    }
    display();
    return 0;
}

void display()
{
    printf("Quotient (Q): %u\t", Q);
    printf("Reminder (A): %u\n", A);
}