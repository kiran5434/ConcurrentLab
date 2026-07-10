class Buffer {
    private int item;
    private boolean available = false;

    // Producer method
    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait(); // Wait if buffer is full
        }

        item = value;
        available = true;
        System.out.println("Produced: " + item);

        notify(); // Notify consumer
    }

    // Consumer method
    public synchronized int consume() throws InterruptedException {
        while (!available) {
            wait(); // Wait if buffer is empty
        }

        System.out.println("Consumed: " + item);
        available = false;

        notify(); // Notify producer
        return item;
    }
}

// Producer Thread
class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
                Thread.sleep(500); // Simulate production time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Consumer Thread
class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.consume();
                Thread.sleep(800); // Simulate consumption time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Main Class
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}
