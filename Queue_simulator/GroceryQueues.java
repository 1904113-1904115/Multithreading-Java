import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueues {
    private List<GroceryQueue> queues;
    private final Cashier cashiers[];
    private Lock lock;
    int totalCustomersServed = 0;
    int totalServiceTime = 0;
    private volatile boolean running;

    public GroceryQueues(int nCashiers, int maxLength) {
        this.queues = new ArrayList<>();
        this.running = true;
        this.cashiers = new Cashier[nCashiers];
        this.lock = new ReentrantLock();
        for (int i = 0; i < nCashiers; i++) {
            GroceryQueue queue = new GroceryQueue(maxLength);
            queues.add(queue);
            cashiers[i] = new Cashier(queue, this);
            cashiers[i].start();
        }
    }

    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            GroceryQueue shortestQueue = queues.get(0);
            for (GroceryQueue queue : queues) {
                if (queue.getQueueSize() < shortestQueue.getQueueSize()) {
                    shortestQueue = queue;
                }
            }
            if (shortestQueue.getQueueSize() < shortestQueue.maxLength) {
                shortestQueue.addCustomer(customer);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public int getTotalServedCustomers() {
        return totalCustomersServed;
    }
}
