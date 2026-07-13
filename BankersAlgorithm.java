import java.util.Scanner;

public class BankersAlgorithm {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int processes = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int resources = sc.nextInt();

        int allocation[][] = new int[processes][resources];
        int max[][] = new int[processes][resources];
        int need[][] = new int[processes][resources];
        int available[] = new int[resources];

        System.out.println("\nEnter Allocation Matrix:");

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nEnter Maximum Matrix:");

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nEnter Available Resources:");

        for (int i = 0; i < resources; i++) {
            available[i] = sc.nextInt();
        }


        // Calculate Need Matrix

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }


        // Display Need Matrix

        System.out.println("\nNeed Matrix:");

        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }


        // Safety Algorithm

        boolean finish[] = new boolean[processes];

        int safeSequence[] = new int[processes];

        int work[] = new int[resources];

        for (int i = 0; i < resources; i++) {
            work[i] = available[i];
        }


        int count = 0;

        while (count < processes) {

            boolean found = false;

            for (int i = 0; i < processes; i++) {

                if (!finish[i]) {

                    int j;

                    for (j = 0; j < resources; j++) {

                        if (need[i][j] > work[j])
                            break;
                    }


                    if (j == resources) {

                        for (int k = 0; k < resources; k++) {
                            work[k] += allocation[i][k];
                        }

                        safeSequence[count++] = i;

                        finish[i] = true;

                        found = true;
                    }
                }
            }


            if (!found) {
                break;
            }
        }


        // Checking Safe State

        if (count == processes) {

            System.out.println("\nSystem is in SAFE state.");

            System.out.print("Safe Sequence: ");

            for (int i = 0; i < processes; i++) {
                System.out.print("P" + safeSequence[i]);

                if (i != processes - 1)
                    System.out.print(" -> ");
            }

        } else {

            System.out.println("\nSystem is NOT in SAFE state.");
            System.out.println("Deadlock may occur.");

        }

        sc.close();
    }
}
