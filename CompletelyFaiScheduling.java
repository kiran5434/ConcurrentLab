write a java program for first come first serve scheduling algorithm

Below is a simple Java program to demonstrate the First Come First Serve (FCFS) CPU Scheduling Algorithm. It calculates the Waiting Time (WT) and Turnaround Time (TAT) for each process.

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
Sample Input
Enter the number of processes: 4
Enter Burst Time for Process P1: 6
Enter Burst Time for Process P2: 8
Enter Burst Time for Process P3: 7
Enter Burst Time for Process P4: 3
Sample Output
FCFS Scheduling Results
------------------------------------------------------------
Process Burst Time Waiting Time Turnaround Time
------------------------------------------------------------
P1      6          0            6
P2      8          6            14
P3      7          14           21
P4      3          21           24
------------------------------------------------------------
Average Waiting Time = 10.25
Average Turnaround Time = 16.25
Time Complexity
Waiting Time Calculation: O(n)
Turnaround Time Calculation: O(n)
Overall Time Complexity: O(n)
Space Complexity: O(n)

This implementation assumes that all processes arrive at time 0 and are executed in the order they are entered, which is the basic FCFS scheduling algorithm commonly used in Operating Systems laboratory exercises.

write a java program for shortest job first scheduling algorithm

Below is a Java program to demonstrate the Shortest Job First (SJF) Scheduling Algorithm (Non-Preemptive). It schedules processes based on the shortest burst time and calculates the Waiting Time (WT) and Turnaround Time (TAT).

import java.util.Scanner;

public class SJFScheduling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] process = new int[n];
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Input burst times
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            System.out.print("Enter Burst Time for Process P" + process[i] + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Sort processes based on Burst Time (Ascending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (burstTime[i] > burstTime[j]) {

                    // Swap Burst Times
                    int temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    // Swap Process IDs
                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                }
            }
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

        System.out.println("\nSJF Scheduling Results");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" +
                    burstTime[i] + "\t\t" +
                    waitingTime[i] + "\t\t" +
                    turnaroundTime[i]);

            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("Average Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}
Sample Input
Enter the number of processes: 4
Enter Burst Time for Process P1: 6
Enter Burst Time for Process P2: 8
Enter Burst Time for Process P3: 7
Enter Burst Time for Process P4: 3
Sample Output
SJF Scheduling Results
----------------------------------------------------------------
Process Burst Time Waiting Time Turnaround Time
----------------------------------------------------------------
P4      3          0            3
P1      6          3            9
P3      7          9            16
P2      8          16           24
----------------------------------------------------------------
Average Waiting Time = 7.00
Average Turnaround Time = 13.00
Algorithm
Read the number of processes and their burst times.
Sort the processes in ascending order of burst time.
Calculate the waiting time for each process.
Calculate the turnaround time using:
Turnaround Time = Waiting Time + Burst Time
Display the scheduling table along with the average waiting time and average turnaround time.
Time Complexity
Sorting: O(n²) (using Bubble Sort)
Waiting Time Calculation: O(n)
Turnaround Time Calculation: O(n)
Overall Time Complexity: O(n²)
Space Complexity: O(n)

Note: This implementation demonstrates the Non-Preemptive SJF scheduling algorithm and assumes that all processes arrive at time 0. It is suitable for Operating Systems laboratory exercises.

write a java program for Linux's Completely Fair 
  scheduling algorithm

Below is a Java program to simulate Linux's Completely Fair Scheduler (CFS). Since the actual Linux CFS is implemented inside the Linux kernel using a Red-Black Tree, this program provides a simplified simulation suitable for Operating Systems laboratory experiments.

Java Program: Simplified Completely Fair Scheduling (CFS)
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
