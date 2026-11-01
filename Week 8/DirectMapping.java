import java.util.Scanner;

public class DirectMapping {
    // Print a number using exactly width bits.
    private static String binary(int value, int width) {
        if (width == 0) {
            return "-";
        }
        return String.format("%" + width + "s", Integer.toBinaryString(value))
                .replace(' ', '0');
    }

    private static int log2(int value) {
        // Direct mapping is simplest when counts are powers of two.
        if (value <= 0 || (value & (value - 1)) != 0) {
            throw new IllegalArgumentException("Memory and cache block counts must be powers of two.");
        }
        return Integer.numberOfTrailingZeros(value);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of main memory in bytes: ");
        int mainMemory = in.nextInt();
        System.out.print("Enter the size of cache memory in bytes: ");
        int cacheMemory = in.nextInt();
        System.out.print("Enter the block size in bytes: ");
        int blockSize = in.nextInt();
        in.close();

        if (mainMemory <= 0 || cacheMemory <= 0 || blockSize <= 0
                || mainMemory % blockSize != 0 || cacheMemory % blockSize != 0) {
            throw new IllegalArgumentException("Sizes must be positive multiples of the block size.");
        }

        int blocks = mainMemory / blockSize; // Main-memory block count.
        int lines = cacheMemory / blockSize; // Cache-line count.
        int biBits = log2(blocks);
        int ciBits = log2(lines);
        int tagBits = biBits - ciBits;
        if (tagBits < 0) {
            throw new IllegalArgumentException("Cache cannot contain more lines than main memory blocks.");
        }

        System.out.println("\nNo. of blocks in main memory: " + blocks);
        System.out.println("No. of Lines in cache memory: " + lines);
        System.out.printf("Bit Widths -> BI: %d bits | CI: %d bits | TI: %d bits%n%n",
                biBits, ciBits, tagBits);
        System.out.println("| Block Index (BI) | Cache Index (CI) | Tag Index (TI) |");
        System.out.println("|------------------|------------------|----------------|");
        for (int block = 0; block < blocks; block++) {
            int cacheLine = block % lines; // BI modulo number of lines.
            int tag = block / lines; // Remaining upper bits.
            System.out.printf("| %-16s | %-16s | %-14s |%n",
                    binary(block, biBits), binary(cacheLine, ciBits), binary(tag, tagBits));
        }
    }
}
