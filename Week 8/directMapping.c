#include <stdio.h>
#include <math.h>

void printBinary(int n, int bits) {
    for (int i = bits - 1; i >= 0; i--) {
        int k = (n >> i) & 1;
        printf("%d", k);
    }
}

int main() {
    int mm_size = 0, cm_size = 0, block_size = 0;

    printf("Enter the size of main memory in bytes: ");
    scanf("%d", &mm_size);
    printf("Enter the size of cache memory in bytes: ");
    scanf("%d", &cm_size);
    printf("Enter the block size in bytes: ");
    scanf("%d", &block_size);

    int noOfBlocks = mm_size / block_size;
    int noOfLines = cm_size / block_size;

    int bi_bits = (int)log2(noOfBlocks);
    int ci_bits = (int)log2(noOfLines);
    int ti_bits = bi_bits - ci_bits;

    printf("\nNo. of blocks in main memory: %d\n", noOfBlocks);
    printf("No. of Lines in cache memory: %d\n", noOfLines);
    printf("Bit Widths -> BI: %d bits | CI: %d bits | TI: %d bits\n\n", bi_bits, ci_bits, ti_bits);

    printf("| Block Index (BI) | Cache Index (CI) | Tag Index (TI) |\n");
    printf("|------------------|------------------|----------------|\n");

    for (int bi = 0; bi < noOfBlocks; bi++) {
        int ci = bi % noOfLines;
        int ti = bi / noOfLines;

        printf("| ");
        printBinary(bi, bi_bits);
        printf("         | ");
        printBinary(ci, ci_bits);
        printf("         | ");
        printBinary(ti, ti_bits);
        printf("        |\n");
    }

    return 0;
}