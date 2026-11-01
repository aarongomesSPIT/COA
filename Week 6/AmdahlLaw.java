public class AmdahlLaw {
    // Speedup = 1 / (serial part + parallel part / processors).
    public static double speedup(double parallelFraction, int processors) {
        return 1.0 / ((1.0 - parallelFraction) + parallelFraction / processors);
    }

    public static void main(String[] args) {
        double[] parallelFractions = {0.5, 0.75, 0.9, 0.95, 0.99};
        int[] processors = {1, 4, 9, 16}; // Same values used by the notebook.

        System.out.println("Amdahl's Law (plotting omitted)");
        for (double fraction : parallelFractions) {
            System.out.printf("%nParallel fraction P = %.2f, maximum speedup = %.4f%n",
                    fraction, 1.0 / (1.0 - fraction));
            for (int processorCount : processors) {
                double speedup = speedup(fraction, processorCount);
                System.out.printf("Processors: %d, Speedup: %.4f, Efficiency: %.4f%n",
                        processorCount, speedup, speedup / processorCount);
            }
        }
    }
}
