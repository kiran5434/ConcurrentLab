import java.util.*;

public class PageReplacementSimulator {

    // FIFO Algorithm
    static void FIFO(int pages[], int frames) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();

        int hits = 0, faults = 0;

        for (int page : pages) {
            if (set.contains(page)) {
                hits++;
            } else {
                faults++;
                if (queue.size() == frames) {
                    int removed = queue.poll();
                    set.remove(removed);
                }
                queue.add(page);
                set.add(page);
            }

            System.out.println("Frames: " + queue);
        }

        System.out.println("\nFIFO Results");
        System.out.println("Page Hits   : " + hits);
        System.out.println("Page Faults : " + faults);
    }

    // LRU Algorithm
    static void LRU(int pages[], int frames) {
        List<Integer> memory = new ArrayList<>();

        int hits = 0, faults = 0;

        for (int page : pages) {

            if (memory.contains(page)) {
                hits++;
                memory.remove((Integer) page);
                memory.add(page);
            } else {
                faults++;
                if (memory.size() == frames)
                    memory.remove(0);

                memory.add(page);
            }

            System.out.println("Frames: " + memory);
        }

        System.out.println("\nLRU Results");
        System.out.println("Page Hits   : " + hits);
        System.out.println("Page Faults : " + faults);
    }

    // Optimal Algorithm
    static void OPT(int pages[], int frames) {

        List<Integer> memory = new ArrayList<>();

        int hits = 0, faults = 0;

        for (int i = 0; i < pages.length; i++) {

            int page = pages[i];

            if (memory.contains(page)) {
                hits++;
            } else {

                faults++;

                if (memory.size() < frames) {
                    memory.add(page);
                } else {

                    int farthest = -1;
                    int index = -1;

                    for (int j = 0; j < memory.size(); j++) {

                        int nextUse = Integer.MAX_VALUE;

                        for (int k = i + 1; k < pages.length; k++) {
                            if (memory.get(j) == pages[k]) {
                                nextUse = k;
                                break;
                            }
                        }

                        if (nextUse > farthest) {
                            farthest = nextUse;
                            index = j;
                        }
                    }

                    memory.set(index, page);
                }
            }

            System.out.println("Frames: " + memory);
        }

        System.out.println("\nOptimal Results");
        System.out.println("Page Hits   : " + hits);
        System.out.println("Page Faults : " + faults);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int pages[] = new int[n];

        System.out.println("Enter page reference string:");

        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        while (true) {

            System.out.println("\n------ PAGE REPLACEMENT SIMULATOR ------");
            System.out.println("1. FIFO");
            System.out.println("2. LRU");
            System.out.println("3. Optimal");
            System.out.println("4. Exit");
            System.out.print("Choose Algorithm: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    FIFO(pages, frames);
                    break;

                case 2:
                    LRU(pages, frames);
                    break;

                case 3:
                    OPT(pages, frames);
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

