import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Task {
    int id;
    int executionTime;
    int period;

    Task(int id, int executionTime, int period) {
        this.id = id;
        this.executionTime = executionTime;
        this.period = period;
    }
}

public class RateMonotonicScheduling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int n = sc.nextInt();

        Task[] tasks = new Task[n];

        // Input task details
        for (int i = 0; i < n; i++) {
            System.out.println("\nTask T" + (i + 1));

            System.out.print("Execution Time: ");
            int execution = sc.nextInt();

            System.out.print("Period: ");
            int period = sc.nextInt();

            tasks[i] = new Task(i + 1, execution, period);
        }

        // Sort tasks based on period (shorter period = higher priority)
        Arrays.sort(tasks, Comparator.comparingInt(t -> t.period));

        System.out.println("\nRate Monotonic Scheduling");
        System.out.println("----------------------------------------------");
        System.out.println("Priority\tTask\tExecution\tPeriod");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\tT" + tasks[i].id +
                    "\t" + tasks[i].executionTime +
                    "\t\t" + tasks[i].period);
        }

        // CPU Utilization
        double utilization = 0.0;

        for (Task task : tasks) {
            utilization += (double) task.executionTime / task.period;
        }

        System.out.println("----------------------------------------------");
        System.out.printf("CPU Utilization = %.3f (%.2f%%)%n",
                utilization, utilization * 100);

        if (utilization <= 0.693) {
            System.out.println("Tasks are schedulable under RMS.");
        } else {
            System.out.println("Tasks may not be schedulable under RMS.");
        }

        sc.close();
    }
}
