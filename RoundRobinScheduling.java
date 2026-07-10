import java.util.Scanner;

public class RoundRobinScheduling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] process = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Input Burst Times
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            System.out.print("Enter Burst Time for Process P" + process[i] + ": ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        int time = 0;
        boolean done;

        // Round Robin Scheduling
        do {
            done = true;

            for (int i = 0; i < n; i++) {

                if (remainingTime[i] > 0) {
                    done = false;

                    if (remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        time += remainingTime[i];
                        waitingTime[i] = time - burstTime[i];
                        remainingTime[i] = 0;
                    }
                }
            }

        } while (!done);

        // Calculate Turnaround Time
        double totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
        }

        // Display Results
        System.out.println("\nRound Robin Scheduling Results");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" +
                    burstTime[i] + "\t\t" +
                    waitingTime[i] + "\t\t" +
                    turnaroundTime[i]);
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("Average Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}
