import java.util.Scanner;

public class pipelining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Take User Input
        System.out.print("Number of Instructions (n) = ");
        int n = scanner.nextInt();

        System.out.print("Pipeline Stages (k) = ");
        int k = scanner.nextInt();

        // 2. Define the sample instructions (hardcoded for simple simulation)
        String i1 = "ADD R1,R2,R3";
        String i2 = "SUB R4,R1,R5";
        String i3 = "AND R6,R7,R8";

        System.out.println("\n5-STAGE PIPELINE SIMULATION\n");
        System.out.printf("%-7s %-15s %-15s %-15s %-15s %-15s%n", "Cycle", "IF", "ID", "EX", "MEM", "WB");

        // 3. Print Cycle-by-Cycle Table
        for (int cycle = 1; cycle <= 10; cycle++) {
            String ifStage = "--", idStage = "--", exStage = "--", memStage = "--", wbStage = "--";

            switch (cycle) {
                case 1: ifStage = i1; break;
                case 2: ifStage = i2; idStage = i1; break;
                case 3: ifStage = i3; idStage = i2; exStage = i1; break;
                case 4: ifStage = i3; idStage = i2; exStage = "STALL"; memStage = i1; break;
                case 5: ifStage = i3; idStage = i2; exStage = "STALL"; memStage = "STALL"; wbStage = i1; break;
                case 6: idStage = i3; exStage = i2; memStage = "STALL"; wbStage = "STALL"; break;
                case 7: exStage = i3; memStage = i2; wbStage = "STALL"; break;
                case 8: memStage = i3; wbStage = i2; break;
                case 9: wbStage = i3; break;
                case 10: break;
            }

            System.out.printf("%-7d %-15s %-15s %-15s %-15s %-15s%n", cycle, ifStage, idStage, exStage, memStage, wbStage);
        }

        // 4. Calculations (Lab Manual Formulas)
        int totalStalls = 2;
        int nonPipelinedCycles = n * k;                      // T_np = n * k
        int idealPipelinedCycles = k + (n - 1);             // T_p = k + (n - 1)
        int actualPipelinedCycles = idealPipelinedCycles + totalStalls;

        // Theoretical (Ideal) Metrics
        float idealSpeedup = (float) nonPipelinedCycles / idealPipelinedCycles;
        float idealEfficiency = idealSpeedup / k;

        // Actual (With Hazard Stalls) Metrics
        float actualSpeedup = (float) nonPipelinedCycles / actualPipelinedCycles;
        float actualEfficiency = actualSpeedup / k;

        // 5. Display Summary Results
        System.out.println("\n--- SIMULATION SUMMARY ---");
        System.out.println("Total Instructions: " + n);
        System.out.println("Total Stall Cycles: " + totalStalls);
        System.out.println("Ideal Cycles: " + idealPipelinedCycles);
        System.out.println("Extra Cycles: " + totalStalls);

        System.out.println("\n--- PERFORMANCE METRICS (IDEAL) ---");
        System.out.println("Non-Pipelined Cycles = " + nonPipelinedCycles);
        System.out.println("Pipelined Cycles = " + idealPipelinedCycles);
        System.out.printf("Speedup = %.2f%n", idealSpeedup);
        System.out.printf("Efficiency = %.2f (%.1f%%)%n", idealEfficiency, idealEfficiency * 100);

        System.out.println("\n--- PERFORMANCE METRICS (ACTUAL WITH STALLS) ---");
        System.out.println("Actual Pipelined Cycles = " + actualPipelinedCycles);
        System.out.printf("Actual Speedup = %.2f%n", actualSpeedup);
        System.out.printf("Actual Efficiency = %.2f (%.1f%%)%n", actualEfficiency, actualEfficiency * 100);

        scanner.close();
    }
}
