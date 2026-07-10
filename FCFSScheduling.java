import java.util.Scanner;

public class FCFSScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        int[] process = new int[n];
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Input Process IDs and Burst Times
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            System.out.print("Enter Burst Time for Process P" + process[i] + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Calculate Waiting Time
        waitingTime[0] = 0;
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        // Calculate Turnaround Time
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
        }

        // Display Results
        double totalWT = 0, totalTAT = 0;

        System.out.println("\nFCFS Scheduling Results");
        System.out.println("------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" +
                    burstTime[i] + "\t\t" +
                    waitingTime[i] + "\t\t" +
                    turnaroundTime[i]);

            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("Average Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}
