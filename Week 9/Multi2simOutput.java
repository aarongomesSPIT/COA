public class Multi2simOutput {
    public static void main(String[] args) {
        // Simulation data from the notebook.
        int[] cores = {1, 2, 4, 8};
        long[] cycles = {822085, 822085, 822085, 822085};
        long singleCoreCycles = cycles[0];

        System.out.println("Multi2Sim results (plotting omitted)");
        System.out.println("Cores\tCycles\tMeasured Speedup\tIdeal Speedup");
        for (int i = 0; i < cores.length; i++) {
            double measuredSpeedup = (double) singleCoreCycles / cycles[i];
            System.out.printf("%d\t%d\t%.2f\t\t%.2f%n",
                    cores[i], cycles[i], measuredSpeedup, (double) cores[i]);
        }
    }
}
