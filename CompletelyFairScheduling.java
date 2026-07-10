import java.util.*;

class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int vruntime;

    Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.vruntime = 0;
    }
}

public class CompletelyFairScheduling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Burst Time for Process P" + i + ": ");
            int bt = sc.nextInt();
            processes.add(new Process(i, bt));
        }

        System.out.print("Enter Time Slice: ");
        int quantum = sc.nextInt();

        int time = 0;

        System.out.println("\nExecution Order:");

        while (true) {

            // Sort processes according to minimum virtual runtime
            Collections.sort(processes, Comparator.comparingInt(p -> p.vruntime));

            boolean completed = true;

            for (Process p : processes) {

                if (p.remainingTime > 0) {

                    completed = false;

                    int execute = Math.min(quantum, p.remainingTime);

                    System.out.println("Time " + time +
                            " --> Process P" + p.pid +
                            " executes for " + execute + " units");

                    time += execute;
                    p.remainingTime -= execute;

                    // Update virtual runtime
                    p.vruntime += execute;
                }
            }

            if (completed)
                break;
        }

        System.out.println("\nProcess Summary");
        System.out.println("-------------------------------------------");
        System.out.println("Process\tBurst Time\tVirtual Runtime");

        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t\t" +
                    p.burstTime + "\t\t" +
                    p.vruntime);
        }

        System.out.println("-------------------------------------------");
        System.out.println("Total Execution Time = " + time);

        sc.close();
    }
}
