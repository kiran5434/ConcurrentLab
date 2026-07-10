class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}

class MyThread extends Thread {

    Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }
}

public class Synch{

    public static void main(String[] args) throws Exception {

        Counter counter = new Counter();

        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter Value = " + counter.count);
    }
}
