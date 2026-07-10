import java.util.Scanner;

public class MultiLevelQueueScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Queue 1 - Round Robin
        System.out.print("Enter number of System Processes (Queue 1): ");
        int n1 = sc.nextInt();

        int[] bt1 = new int[n1];
        int[] remBT = new int[n1];
        int[] wt1 = new int[n1];
        int[] tat1 = new int[n1];

        for (int i = 0; i < n1; i++) {
            System.out.print("Enter Burst Time of System Process P" + (i + 1) + ": ");
            bt1[i] = sc.nextInt();
            remBT[i] = bt1[i];
        }

        // Queue 2 - FCFS
        System.out.print("\nEnter number of User Processes (Queue 2): ");
        int n2 = sc.nextInt();

        int[] bt2 = new int[n2];
        int[] wt2 = new int[n2];
        int[] tat2 = new int[n2];

        for (int i = 0; i < n2; i++) {
            System.out.print("Enter Burst Time of User Process U" + (i + 1) + ": ");
            bt2[i] = sc.nextInt();
        }

        int timeQuantum = 2;
        int currentTime = 0;

        // Queue 1 - Round Robin Scheduling
        boolean done;
        do {
            done = true;
            for (int i = 0; i < n1; i++) {
                if (remBT[i] > 0) {
                    done = false;

                    if (remBT[i] > timeQuantum) {
                        currentTime += timeQuantum;
                        remBT[i] -= timeQuantum;
                    } else {
                        currentTime += remBT[i];
                        wt1[i] = currentTime - bt1[i];
                        remBT[i] = 0;
                    }
                }
            }
        } while (!done);

        // Turnaround Time for Queue 1
        for (int i = 0; i < n1; i++) {
            tat1[i] = wt1[i] + bt1[i];
        }

        // Queue 2 - FCFS Scheduling
        if (n2 > 0)
            wt2[0] = currentTime;

        for (int i = 1; i < n2; i++) {
            wt2[i] = wt2[i - 1] + bt2[i - 1];
        }

        for (int i = 0; i < n2; i++) {
            tat2[i] = wt2[i] + bt2[i];
        }

        // Display Queue 1 Results
        System.out.println("\n===== Queue 1 (Round Robin) =====");
        System.out.println("Process\tBT\tWT\tTAT");

        double totalWT1 = 0, totalTAT1 = 0;

        for (int i = 0; i < n1; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt1[i] + "\t" + wt1[i] + "\t" + tat1[i]);
            totalWT1 += wt1[i];
            totalTAT1 += tat1[i];
        }

        System.out.printf("Average Waiting Time = %.2f\n", totalWT1 / n1);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT1 / n1);

        // Display Queue 2 Results
        System.out.println("\n===== Queue 2 (FCFS) =====");
        System.out.println("Process\tBT\tWT\tTAT");

        double totalWT2 = 0, totalTAT2 = 0;

        for (int i = 0; i < n2; i++) {
            System.out.println("U" + (i + 1) + "\t" + bt2[i] + "\t" + wt2[i] + "\t" + tat2[i]);
            totalWT2 += wt2[i];
            totalTAT2 += tat2[i];
        }

        System.out.printf("Average Waiting Time = %.2f\n", totalWT2 / n2);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT2 / n2);

        sc.close();
    }
}
