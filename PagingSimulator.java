import java.util.Scanner;

public class PagingSimulator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter page size: ");
        int pageSize = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int pages = sc.nextInt();

        int[] pageTable = new int[pages];

        System.out.println("\nEnter Page Table (Page -> Frame Mapping)");

        for (int i = 0; i < pages; i++) {
            System.out.print("Frame number for Page " + i + ": ");
            pageTable[i] = sc.nextInt();
        }

        System.out.print("\nEnter Page Number: ");
        int pageNo = sc.nextInt();

        System.out.print("Enter Offset: ");
        int offset = sc.nextInt();

        if (pageNo < 0 || pageNo >= pages) {
            System.out.println("\nPage Fault! Invalid Page Number.");
        } else if (offset >= pageSize || offset < 0) {
            System.out.println("\nInvalid Offset.");
        } else {

            int frameNo = pageTable[pageNo];
            int physicalAddress = frameNo * pageSize + offset;

            System.out.println("\nLogical Address  : (" + pageNo + ", " + offset + ")");
            System.out.println("Frame Number     : " + frameNo);
            System.out.println("Physical Address : " + physicalAddress);
        }

        sc.close();
}
}
