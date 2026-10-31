#include <stdio.h>

int N = 32; // Since 32 bit for int in dividend
unsigned A = 0;
unsigned M, Q;

// M - divisor
// Q - dividend
void display();
void shiftLeft();

int main()
{
    printf("Enter Dividend (Q): ");
    scanf("%u", &Q);
    printf("Enter Divisor (M): ");
    scanf("%u", &M);

    while (N > 0)
    {
        if ((A >> 31) & 1U)
        {
            shiftLeft();
            A = A + M;
        }
        else
        {
            shiftLeft();
            A = A - M;
        }
        
        if ((A >> 31) & 1U)
        {
            Q &= ~(1U);
        }
        else
        {
            Q |= 1U;
        }

        N--;
    }

    if ((A >> 31) & 1U)
    {
        A = A + M;
    }

    
    return 0;
}

void shiftLeft(){
    // Shift Left AQ

    // Shift just A (also take the 31st bit of Q and OR it with A's Last bit)
    A = (A << 1) | ((Q >> 31) & 1U);

    display();

    // Shift just Q
    Q = Q << 1;
}

void display()
{
    printf("Quotient (Q): %u\t", Q);
    printf("Reminder (A): %u\n", A);
}