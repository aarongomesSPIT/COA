import java.util.Arrays;

public class PageReplacement {
    // Reference string and number of available page frames.
    private static final int[] REFERENCES = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3,
            0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
    private static final int FRAME_COUNT = 3;

    private static void fifo() {
        int[] frames = {-1, -1, -1};
        int pointer = 0, hits = 0, faults = 0;
        for (int i = 0; i < REFERENCES.length; i++) {
            boolean hit = contains(frames, REFERENCES[i]); // Page already loaded?
            if (hit) {
                hits++;
            } else {
                frames[pointer] = REFERENCES[i];
                pointer = (pointer + 1) % FRAME_COUNT;
                faults++;
            }
            System.out.printf("%d %s %s%n", i, hit ? "H" : "F", Arrays.toString(frames));
        }
        printTotals("FIFO", faults, hits);
    }

    private static void lru() {
        int[] frames = {-1, -1, -1};
        int[] lastUsed = {-1, -1, -1};
        int hits = 0, faults = 0;
        for (int i = 0; i < REFERENCES.length; i++) {
            int page = REFERENCES[i];
            int index = indexOf(frames, page);
            boolean hit = index >= 0;
            if (hit) {
                lastUsed[index] = i;
                hits++;
            } else {
                // Replace the page whose last-use position is smallest.
                index = indexOf(lastUsed, minimum(lastUsed));
                frames[index] = page;
                lastUsed[index] = i;
                faults++;
            }
            System.out.printf("%d %s %s %s%n", i, hit ? "H" : "F",
                    Arrays.toString(frames), Arrays.toString(lastUsed));
        }
        printTotals("LRU", faults, hits);
    }

    private static boolean contains(int[] values, int target) {
        return indexOf(values, target) >= 0;
    }

    private static int indexOf(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) return i;
        }
        return -1;
    }

    private static int minimum(int[] values) {
        int result = values[0];
        for (int value : values) result = Math.min(result, value);
        return result;
    }

    private static void printTotals(String policy, int faults, int hits) {
        System.out.printf("%n%s: Faults = %d, Hits = %d, Fault/Hit ratio = %.2f%n",
                policy, faults, hits, (double) faults / hits);
    }

    public static void main(String[] args) {
        System.out.println("FIFO Page Replacement");
        fifo();
        System.out.println("\nLRU Page Replacement");
        lru();
    }
}
